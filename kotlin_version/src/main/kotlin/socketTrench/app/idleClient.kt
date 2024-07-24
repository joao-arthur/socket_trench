package socketTrench.app

import socketTrench.events.DefaultDispatcher
import socketTrench.events.Observer
import socketTrench.gui.GUIScreen
import socketTrench.gui.goTo
import socketTrench.socket.*
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.Font
import javax.swing.*

internal class IdleClientObserver : Observer {
    override fun handle(event: String) {
        if (event == SocketMessages.CONN_REFUSED) {
            JOptionPane.showMessageDialog(
                null,
                "It wasn't possible to establish connection!",
                "ERROR!",
                JOptionPane.ERROR_MESSAGE
            )
            return
        }
        if (event == SocketMessages.PORT_IN_USE) {
            JOptionPane.showMessageDialog(
                null,
                "The port '54321' is already in use!",
                "ERROR!",
                JOptionPane.ERROR_MESSAGE
            )
            return
        }
        if (event == SocketMessages.CONN_SERVER) {
            buildMatchScreenPlayer1()
            return
        }
        if (event == SocketMessages.CONN_CLIENT) {
            buildMatchScreenPlayer2()
            return
        }
    }
}

class IdleClientSocketManager(private val dispatcher: DefaultDispatcher) : SocketManager {
    override fun handleMessage(message: String) {
        dispatcher.dispatch(message)
    }
}

internal class IdleClientService(private val dispatcher: DefaultDispatcher) {
    init {
        dispatcher.register(IdleClientObserver())
    }

    fun createClient(ip: String) {
        val conn = SocketClient(SocketService(), ip)
        conn.setManager(IdleClientSocketManager(dispatcher))
        createConnection(conn)
    }
}

internal class IdleClientScreen(private val service: IdleClientService) : GUIScreen {
    private val frame = JFrame()

    init {
        val info = JLabel()
        info.preferredSize = Dimension(400, 50)
        info.maximumSize = Dimension(400, 50)
        info.horizontalAlignment = JLabel.CENTER
        info.alignmentX = JLabel.CENTER_ALIGNMENT
        info.font = Font("Arial", Font.PLAIN, 20)
        info.text = "INPUT THE IP"

        val input = JTextField()
        input.preferredSize = Dimension(400, 50)
        input.maximumSize = Dimension(400, 50)
        input.minimumSize = Dimension(400, 50)
        input.font = Font("Arial", Font.BOLD, 25)
        input.text = "192.168.0.1"

        val confirm = JButton()
        confirm.preferredSize = Dimension(400, 50)
        confirm.maximumSize = Dimension(400, 50)
        confirm.alignmentX = JButton.CENTER_ALIGNMENT
        confirm.text = "CONFIRM"
        confirm.font = Font("Arial", 0, 20)
        confirm.addActionListener { _ ->
            service.createClient(input.text)
        }

        val content = JPanel()
        content.layout = BoxLayout(content, BoxLayout.Y_AXIS)
        content.add(Box.createVerticalGlue())
        content.add(info)
        content.add(input)
        content.add(Box.createVerticalGlue())
        content.add(confirm)
        content.add(Box.createVerticalGlue())
        content.preferredSize = Dimension(Screen.WIDTH, Screen.HEIGHT)

        val container = JPanel(FlowLayout())
        container.layout = BoxLayout(container, BoxLayout.X_AXIS)
        container.add(Box.createHorizontalGlue())
        container.add(content)
        container.add(Box.createHorizontalGlue())
        container.preferredSize = Dimension(Screen.WIDTH, Screen.HEIGHT)

        frame.add(container)
        frame.pack()
        frame.title = "Client | Socket Trench"
        frame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        frame.setLocationRelativeTo(null)
        frame.isResizable = false
        frame.isVisible = true
    }

    override fun dispose() {
        frame.dispose()
    }
}

fun buildIdleClientScreen() {
    val dispatcher = DefaultDispatcher()
    val service = IdleClientService(dispatcher)
    val screen = IdleClientScreen(service)
    goTo(screen)
}
