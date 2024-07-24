package socketTrench.app

import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.Font
import javax.swing.*
import socketTrench.events.*
import socketTrench.gui.*
import socketTrench.socket.*

internal class IdleClientObserver : Observer {
    override fun handle(event: String) {
        if (event == SocketMessages.CONN_REFUSED) {
            JOptionPane.showMessageDialog(null, "It wasn't possible to establish connection!", "ERROR!", JOptionPane.ERROR_MESSAGE)
            return
        }
        if (event == SocketMessages.PORT_IN_USE) {
            JOptionPane.showMessageDialog(null, "The port '54321' is already in use!", "ERROR!", JOptionPane.ERROR_MESSAGE)
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
        info.setPreferredSize(Dimension(400, 50))
        info.setMaximumSize(Dimension(400, 50))
        info.setHorizontalAlignment(JLabel.CENTER)
        info.setAlignmentX(JLabel.CENTER_ALIGNMENT)
        info.setFont(Font("Arial", Font.PLAIN, 20))
        info.setText("INPUT THE IP")

        val input = JTextField()
        input.setPreferredSize(Dimension(400, 50))
        input.setMaximumSize(Dimension(400, 50))
        input.setMinimumSize(Dimension(400, 50))
        input.setFont(Font("Arial", Font.BOLD, 25))
        input.setText("192.168.0.1")

        val confirm = JButton()
        confirm.setPreferredSize(Dimension(400, 50))
        confirm.setMaximumSize(Dimension(400, 50))
        confirm.setAlignmentX(JButton.CENTER_ALIGNMENT)
        confirm.setText("CONFIRM")
        confirm.setFont(Font("Arial", 0, 20))
        confirm.addActionListener { _ ->
            service.createClient(input.getText())
        }

        val content = JPanel()
        content.setLayout(BoxLayout(content, BoxLayout.Y_AXIS))
        content.add(Box.createVerticalGlue())
        content.add(info)
        content.add(input)
        content.add(Box.createVerticalGlue())
        content.add(confirm)
        content.add(Box.createVerticalGlue())
        content.setPreferredSize(Dimension(Screen.WIDTH, Screen.HEIGHT))

        val container = JPanel(FlowLayout())
        container.setLayout(BoxLayout(container, BoxLayout.X_AXIS))
        container.add(Box.createHorizontalGlue())
        container.add(content)
        container.add(Box.createHorizontalGlue())
        container.setPreferredSize(Dimension(Screen.WIDTH, Screen.HEIGHT))

        frame.add(container)
        frame.pack()
        frame.setTitle("Client | Socket Trench")
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
        frame.setLocationRelativeTo(null)
        frame.setResizable(false)
        frame.setVisible(true)
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
