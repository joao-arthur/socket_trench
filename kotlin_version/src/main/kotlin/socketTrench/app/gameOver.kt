package socketTrench.app

import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.Font
import javax.swing.*
import socketTrench.gui.*

internal class GameOverService {
    fun goToMainMenu() {
        buildMainMenuScreen()
    }
}

internal class GameOverScreen(private val service: GameOverService) : GUIScreen {
    private val frame = JFrame()

    init {
        val info = JLabel()
        info.setPreferredSize(Dimension(300, 50))
        info.setMaximumSize(Dimension(300, 50))
        info.setHorizontalAlignment(JLabel.CENTER)
        info.setAlignmentX(JLabel.CENTER_ALIGNMENT)
        info.setFont(Font("Arial", Font.PLAIN, 20))
        info.setText("GAME OVER!")

        val confirm = JButton()
        confirm.setPreferredSize(Dimension(300, 50))
        confirm.setMaximumSize(Dimension(300, 50))
        confirm.setHorizontalAlignment(JButton.CENTER)
        confirm.setAlignmentX(JButton.CENTER_ALIGNMENT)
        confirm.setText("OK")
        confirm.setFont(Font("Arial", 0, 20))
        confirm.addActionListener { _ ->
            service.goToMainMenu()
        }

        val content = JPanel()
        content.setLayout(BoxLayout(content, BoxLayout.Y_AXIS))
        content.add(Box.createVerticalGlue())
        content.add(info)
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
        frame.setTitle("Game Over | Socket Trench")
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
        frame.setLocationRelativeTo(null)
        frame.setResizable(false)
        frame.setVisible(true)
    }

    override fun dispose() {
        frame.dispose()
    }
}

fun buildGameOverScreen() {
    val service = GameOverService()
    val screen = GameOverScreen(service)
    goTo(screen)
}
 