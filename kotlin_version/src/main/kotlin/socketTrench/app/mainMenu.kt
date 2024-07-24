package socketTrench.app

import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.Font
import javax.swing.*
import socketTrench.gui.*

internal class MainMenuService {
    fun goToIdleServer() {
        buildIdleServerScreen()
    }

    fun goToIdleClient() {
        buildIdleClientScreen()
    }
}

internal class MainMenuScreen(private val service: MainMenuService) : GUIScreen {
    private val frame = JFrame()

    init {
        val createMatch = JButton()
        createMatch.setPreferredSize(Dimension(250, 50))
        createMatch.setMaximumSize(Dimension(250, 50))
        createMatch.setText("CREATE MATCH")
        createMatch.setFont(Font("Arial", 0, 20))
        createMatch.addActionListener { _ ->
            service.goToIdleServer()
        }

        val connectMatch = JButton()
        connectMatch.setPreferredSize(Dimension(250, 50))
        connectMatch.setMaximumSize(Dimension(250, 50))
        connectMatch.setText("CONNECT TO MATCH")
        connectMatch.setFont(Font("Arial", 0, 20))
        connectMatch.addActionListener { _ ->
            service.goToIdleClient()
        }

        val content = JPanel()
        content.setLayout(BoxLayout(content, BoxLayout.Y_AXIS))
        content.add(Box.createVerticalGlue())
        content.add(createMatch)
        content.add(Box.createVerticalGlue())
        content.add(connectMatch)
        content.add(Box.createVerticalGlue())

        val container = JPanel(FlowLayout())
        container.setLayout(BoxLayout(container, BoxLayout.X_AXIS))
        container.add(Box.createHorizontalGlue())
        container.add(content)
        container.add(Box.createHorizontalGlue())
        container.setPreferredSize(Dimension(Screen.WIDTH, Screen.HEIGHT))

        frame.add(container)
        frame.pack()
        frame.setTitle("Socket Trench")
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
        frame.setLocationRelativeTo(null)
        frame.setResizable(false)
        frame.setVisible(true)
    }

    override fun dispose() {
        frame.dispose()
    }
}

fun buildMainMenuScreen() {
    val service = MainMenuService()
    val screen = MainMenuScreen(service)
    goTo(screen)
}
