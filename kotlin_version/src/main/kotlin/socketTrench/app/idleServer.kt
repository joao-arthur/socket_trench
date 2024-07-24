package socketTrench.app

import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.Font
import java.util.logging.Level
import java.util.logging.Logger
import javax.swing.*
import socketTrench.events.*
import socketTrench.gui.*
import socketTrench.socket.*
import java.net.NetworkInterface

internal class IdleServerObserver : Observer {
    override fun handle(event: String) {
        if (event == SocketMessages.CONN_REFUSED) {
            JOptionPane.showMessageDialog( null, "It wasn't possible to establish connection!", "ERROR!", JOptionPane.ERROR_MESSAGE  )
            return
        }
        if (event == SocketMessages.PORT_IN_USE) {
            JOptionPane.showMessageDialog( null, "The port '54321' is already in use!", "ERROR!", JOptionPane.ERROR_MESSAGE
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

class IdleServerSocketManager(private val dispatcher: DefaultDispatcher) : SocketManager {
    override fun handleMessage(message: String) {
        dispatcher.dispatch(message)
    }
}

internal class IdleServerService(private val dispatcher: DefaultDispatcher) {
    init {
        dispatcher.register(IdleServerObserver())
    }

    fun createServer() {
        val conn = SocketServer(SocketService())
        conn.setManager(IdleServerSocketManager(dispatcher))
        createConnection(conn)
    }

    fun getLocalIP(): String? {
        try {
            val networkInterfaces = NetworkInterface.getNetworkInterfaces()
            while (networkInterfaces.hasMoreElements()) {
                val networkInterface = networkInterfaces.nextElement()
                val inetAddresses = networkInterface.inetAddresses
                while (inetAddresses.hasMoreElements()) {
                    val inetAddress = inetAddresses.nextElement()
                    if (!inetAddress.isLoopbackAddress && !(inetAddress.hostAddress.contains(":"))) {
                        return inetAddress.hostAddress
                    }
                }
            }
        } catch (e: Exception) {
            Logger.getLogger("IdleServerService").log(Level.SEVERE, null, e)
            return null
        }
        return null
    }
}

internal class IdleServerScreen(private val service: IdleServerService) : GUIScreen {
    private val frame = JFrame()

    init {
        initComponents()
        service.createServer()
    }

    private fun initComponents() {
        val info = JLabel()
        info.setPreferredSize(Dimension(400, 35))
        info.setMaximumSize(Dimension(400, 35))
        info.setHorizontalAlignment(JLabel.CENTER)
        info.setFont(Font("Arial", Font.PLAIN, 20))
        info.setText("CONNECT TO THE IP")

        val ipValue = JLabel()
        ipValue.setPreferredSize(Dimension(400, 35))
        ipValue.setMaximumSize(Dimension(400, 35))
        ipValue.setHorizontalAlignment(JLabel.CENTER)
        ipValue.setFont(Font("Arial", Font.BOLD, 25))
        ipValue.setText(service.getLocalIP())

        val content = JPanel()
        content.setLayout(BoxLayout(content, BoxLayout.Y_AXIS))
        content.add(Box.createVerticalGlue())
        content.add(info)
        content.add(ipValue)
        content.add(Box.createVerticalGlue())
        content.add(Box.createVerticalGlue())

        val container = JPanel(FlowLayout())
        container.setLayout(BoxLayout(container, BoxLayout.X_AXIS))
        container.add(Box.createHorizontalGlue())
        container.add(content)
        container.add(Box.createHorizontalGlue())
        container.setPreferredSize(Dimension(Screen.WIDTH, Screen.HEIGHT))

        frame.add(container)
        frame.pack()
        frame.setTitle("Server | Socket Trench")
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
        frame.setLocationRelativeTo(null)
        frame.setResizable(false)
        frame.setVisible(true)
    }

    override fun dispose() {
        frame.dispose()
    }
}

fun buildIdleServerScreen() {
    val dispatcher = DefaultDispatcher()
    val service = IdleServerService(dispatcher)
    val screen = IdleServerScreen(service)
    goTo(screen)
}
