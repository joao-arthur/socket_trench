package socketTrench.app

import socketTrench.gui.GUIScreen
import socketTrench.gui.goTo
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.Font
import javax.swing.*

internal class YouWonService {
    fun goToMainMenu() {
        buildMainMenuScreen()
    }
}

internal class YouWonScreen(private val service: YouWonService) : GUIScreen {
    private val frame = JFrame()

    init {
        val info = JLabel()
        info.preferredSize = Dimension(300, 50)
        info.maximumSize = Dimension(300, 50)
        info.horizontalAlignment = JLabel.CENTER
        info.alignmentX = JLabel.CENTER_ALIGNMENT
        info.font = Font("Arial", Font.PLAIN, 20)
        info.text = "YOU WON!"

        val confirm = JButton()
        confirm.preferredSize = Dimension(300, 50)
        confirm.maximumSize = Dimension(300, 50)
        confirm.horizontalAlignment = JButton.CENTER
        confirm.alignmentX = JButton.CENTER_ALIGNMENT
        confirm.text = "OK"
        confirm.font = Font("Arial", 0, 20)
        confirm.addActionListener { _ ->
            service.goToMainMenu()
        }

        val content = JPanel()
        content.layout = BoxLayout(content, BoxLayout.Y_AXIS)
        content.add(Box.createVerticalGlue())
        content.add(info)
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
        frame.title = "You won | Socket Trench"
        frame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        frame.setLocationRelativeTo(null)
        frame.isResizable = false
        frame.isVisible = true
    }

    override fun dispose() {
        frame.dispose()
    }
}

fun buildYouWonScreen() {
    val service = YouWonService()
    val screen = YouWonScreen(service)
    goTo(screen)
}
