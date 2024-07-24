package socketTrench.app

import socketTrench.gui.GUIScreen
import socketTrench.gui.goTo
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.Font
import javax.swing.*

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
        createMatch.preferredSize = Dimension(250, 50)
        createMatch.maximumSize = Dimension(250, 50)
        createMatch.text = "CREATE MATCH"
        createMatch.font = Font("Arial", 0, 20)
        createMatch.addActionListener { _ ->
            service.goToIdleServer()
        }

        val connectMatch = JButton()
        connectMatch.preferredSize = Dimension(250, 50)
        connectMatch.maximumSize = Dimension(250, 50)
        connectMatch.text = "CONNECT TO MATCH"
        connectMatch.font = Font("Arial", 0, 20)
        connectMatch.addActionListener { _ ->
            service.goToIdleClient()
        }

        val content = JPanel()
        content.layout = BoxLayout(content, BoxLayout.Y_AXIS)
        content.add(Box.createVerticalGlue())
        content.add(createMatch)
        content.add(Box.createVerticalGlue())
        content.add(connectMatch)
        content.add(Box.createVerticalGlue())

        val container = JPanel(FlowLayout())
        container.layout = BoxLayout(container, BoxLayout.X_AXIS)
        container.add(Box.createHorizontalGlue())
        container.add(content)
        container.add(Box.createHorizontalGlue())
        container.preferredSize = Dimension(Screen.WIDTH, Screen.HEIGHT)

        frame.add(container)
        frame.pack()
        frame.title = "Socket Trench"
        frame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        frame.setLocationRelativeTo(null)
        frame.isResizable = false
        frame.isVisible = true
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
