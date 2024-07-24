package socketTrench.app

import socketTrench.engine.*
import socketTrench.events.DefaultDispatcher
import socketTrench.events.Observer
import socketTrench.gui.GUIScreen
import socketTrench.gui.goTo
import socketTrench.matchScene.*
import socketTrench.socket.SocketManager
import socketTrench.socket.SocketMessages
import socketTrench.socket.closeConnection
import socketTrench.socket.getConnection
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JFrame
import javax.swing.JPanel

object MatchMessages {
    const val MOVE = "M"
    const val SHOOT = "S"
    const val GAME_OVER = "GO"
    const val YOU_WON = "YW"
}

class MatchObserver(private val engineState: EngineState, private val opponent: Player) : Observer {
    override fun handle(event: String) {
        if (event == SocketMessages.NO_SUCH_ELEMENT) {
            // TODO lost connection
            closeConnection()
            buildYouWonScreen()
            return
        }
        if (event == MatchMessages.GAME_OVER) {
            closeConnection()
            buildGameOverScreen()
            return
        }
        if (event == MatchMessages.YOU_WON) {
            closeConnection()
            buildYouWonScreen()
            return
        }
        if (event == MatchMessages.SHOOT) {
            opponent.shoot(engineState)
            return
        }
        if (event.startsWith(MatchMessages.MOVE)) {
            val eventX = Integer.parseInt(event.split(";")[1])
            val eventY = Integer.parseInt(event.split(";")[2])
            val body = opponent.getBody()
            if (body != null) {
                body.x = eventX
                body.y = eventY
            }
            return
        }
    }
}

class MatchSocketManager(private val dispatcher: DefaultDispatcher) : SocketManager {
    override fun handleMessage(message: String) {
        dispatcher.dispatch(message)
    }
}

class MatchRenderObserver(private val player: Player, private val opponent: Player) : Observer {
    override fun handle(event: String) {
        if (event == EngineMessages.RENDER) {
            if (player.getLifePoints() <= 0) {
                getConnection()?.send(MatchMessages.YOU_WON)
                closeConnection()
                buildGameOverScreen()
                return
            }
            if (opponent.getLifePoints() <= 0) {
                getConnection()?.send(MatchMessages.GAME_OVER)
                closeConnection()
                buildYouWonScreen()
                return
            }
            val body = player.getBody()!!
            val force = player.getForce()!!
            if (force.x != 0 || force.y != 0) {
                getConnection()?.send("${MatchMessages.MOVE};${body.x};${body.y}")
            }
        }
    }
}

internal class MatchPanel(private val engineRenderer: EngineRenderer) : JPanel(), Observer {
    init {
        preferredSize = Dimension(Screen.WIDTH, Screen.HEIGHT)
    }

    override fun paintComponent(graphics: Graphics) {
        super.paintComponent(graphics)
        engineRenderer.render(graphics)
    }

    override fun handle(event: String) {
        if (event == EngineMessages.RENDER) {
            repaint()
        }
    }
}

class MatchInputHandler : InputHandler {
    override fun onKeyPressed(keyCode: Int, engineState: EngineState) {
        when (keyCode) {
            KeyEvent.VK_SPACE -> getConnection()?.send(MatchMessages.SHOOT)
        }
    }

    override fun onKeyReleased(keyCode: Int) {}
}

internal class MatchScreen(
    private val panel: JPanel,
    private val keyListener: KeyListener,
) : GUIScreen {
    private val frame = JFrame()

    init {
        frame.add(panel)
        frame.pack()
        frame.title = "Match | Socket Trench"
        frame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        frame.setLocationRelativeTo(null)
        frame.isResizable = false
        frame.isVisible = true
        frame.addKeyListener(keyListener)
    }

    override fun dispose() {
        frame.dispose()
    }
}

fun buildMatchScreenPlayer1() {
    val player = Player1GameObject(true)
    val opponent = Player2GameObject(false)
    val gameObjects = mutableListOf<GameObject>(
        BackgroundGameObject(),
        player,
        opponent,
        MatchGameObject(MatchInputHandler())
    )

    val matchDispatcher = DefaultDispatcher()
    val engineRenderDispatcher = DefaultDispatcher()

    getConnection()?.setManager(MatchSocketManager(matchDispatcher))

    val engineState = EngineState(gameObjects)
    val renderer = EngineRenderer(gameObjects)
    val manager = EngineManager(gameObjects, engineState, engineRenderDispatcher)
    val keyListener = EngineKeyAdapter(gameObjects, engineState)
    val panel = MatchPanel(renderer)
    val screen = MatchScreen(panel, keyListener)

    val matchRenderObserver = MatchRenderObserver(player, opponent)
    val matchObserver = MatchObserver(engineState, opponent)

    engineRenderDispatcher.register(panel)
    engineRenderDispatcher.register(matchRenderObserver)
    matchDispatcher.register(matchObserver)

    goTo(screen)
    manager.onInit()
}

fun buildMatchScreenPlayer2() {
    val player = Player2GameObject(true)
    val opponent = Player1GameObject(false)
    val gameObjects = mutableListOf<GameObject>(
        BackgroundGameObject(),
        player,
        opponent,
        MatchGameObject(MatchInputHandler())
    )

    val matchDispatcher = DefaultDispatcher()
    val engineRenderDispatcher = DefaultDispatcher()

    getConnection()?.setManager(MatchSocketManager(matchDispatcher))

    val engineState = EngineState(gameObjects)
    val renderer = EngineRenderer(gameObjects)
    val manager = EngineManager(gameObjects, engineState, engineRenderDispatcher)
    val keyListener = EngineKeyAdapter(gameObjects, engineState)
    val panel = MatchPanel(renderer)
    val screen = MatchScreen(panel, keyListener)

    val matchRenderObserver = MatchRenderObserver(player, opponent)
    val matchObserver = MatchObserver(engineState, opponent)

    engineRenderDispatcher.register(panel)
    engineRenderDispatcher.register(matchRenderObserver)
    matchDispatcher.register(matchObserver)

    goTo(screen)
    manager.onInit()
}
