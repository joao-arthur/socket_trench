package socketTrench.matchScene

import java.awt.event.KeyEvent
import kotlin.collections.MutableList
import socketTrench.engine.*
import socketTrench.app.Screen
import socketTrench.imageLoader.loadImageFromPath

interface Player : GameObject {
    fun shoot(engineState: EngineState)
    fun getLifePoints(): Int
}

class BackgroundGameObject : DefaultGameObject() {
    init {
        setBody(BoxDim(0, 0, Screen.WIDTH, Screen.HEIGHT))
        setTexture(loadImageFromPath("background.png"))
    }
}

class MatchGameObject(inputHandler: InputHandler) : DefaultGameObject() {
    init {
        setInputHandler(inputHandler)
    }
}

internal class ShootGameObject(
    x: Int,
    y: Int,
    speedX: Int
) : DefaultGameObject() {
    init {
        setBody(BoxDim(x, y, 4, 2))
        setCollider(BoxDim(x, y, 4, 2))
        setForce(Point(speedX, 0))
        setTexture(loadImageFromPath("shoot.png"))
    }

    override fun onLeaveScreen(engineState: EngineState) {
        engineState.destroy(this)
    }
}


class Player1GameObject(canInput: Boolean) : DefaultGameObject(), Player {
    private var lifePoints = 3

    init {
        setBody(BoxDim(0, 100, 58, 30))
        setCollider(BoxDim(0, 100, 58, 30))
        setTexture(loadImageFromPath("player1.png"))
        setForce(Point(0, 0))
        setBounds(BoxPos(0, 0, 158, Screen.HEIGHT))
        if (canInput) {
            setInputHandler(Player1InputHandler(this))
        }
    }

    override fun shoot(engineState: EngineState) {
        engineState.create(
            ShootGameObject(
                getBody()!!.x + getBody()!!.w,
                getBody()!!.y + getBody()!!.h / 2 - 1,
                10
            )
        )
    }

    override fun getLifePoints(): Int {
        return lifePoints
    }

    override fun onCollideWith(other: GameObject, engineState: EngineState) {
        engineState.destroy(other)
        lifePoints -= 1
    }
}

internal class Player1InputHandler(private var player1: Player1GameObject) : InputHandler {
    override fun onKeyPressed(keyCode: Int, engineState: EngineState) {
        when (keyCode) {
            KeyEvent.VK_LEFT -> player1.getForce()!!.x = -4
            KeyEvent.VK_RIGHT -> player1.getForce()!!.x = 4
            KeyEvent.VK_UP -> player1.getForce()!!.y = -4
            KeyEvent.VK_DOWN -> player1.getForce()!!.y = 4
            KeyEvent.VK_SPACE -> player1.shoot(engineState)
        }
    }

    override fun onKeyReleased(keyCode: Int) {
        when (keyCode) {
            KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> player1.getForce()!!.x = 0
            KeyEvent.VK_UP, KeyEvent.VK_DOWN -> player1.getForce()!!.y = 0
        }
    }
}

class Player2GameObject(canInput: Boolean) : DefaultGameObject(), Player {
    init {
        setBody(BoxDim(Screen.WIDTH - 58, Screen.HEIGHT - 100 - 30, 58, 30))
        setCollider(BoxDim(Screen.WIDTH - 58, Screen.HEIGHT - 100 - 30, 58, 30))
        setTexture(loadImageFromPath("player2.png"))
        setForce(Point(0, 0))
        setBounds(BoxPos(Screen.WIDTH - 158, 0, Screen.WIDTH, Screen.HEIGHT))
        if (canInput) {
            setInputHandler(Player2InputHandler(this))
        }
    }

    override fun shoot(engineState: EngineState) {
        engineState.create(
            ShootGameObject(
                getBody()!!.x - 4,
                getBody()!!.y + getBody()!!.h / 2 - 1,
                -10
            )
        )
    }

    override fun getLifePoints(): Int {
        return 3
    }
}

internal class Player2InputHandler(private val player2: Player2GameObject) : InputHandler {
    override fun onKeyPressed(keyCode: Int, engineState: EngineState) {
        when (keyCode) {
            KeyEvent.VK_LEFT -> player2.getForce()!!.x = -4
            KeyEvent.VK_RIGHT -> player2.getForce()!!.x = 4
            KeyEvent.VK_UP -> player2.getForce()!!.y = -4
            KeyEvent.VK_DOWN -> player2.getForce()!!.y = 4
            KeyEvent.VK_SPACE -> player2.shoot(engineState)
        }
    }

    override fun onKeyReleased(keyCode: Int) {
        when (keyCode) {
            KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> player2.getForce()!!.x = 0
            KeyEvent.VK_UP, KeyEvent.VK_DOWN -> player2.getForce()!!.y = 0
        }
    }
}

fun createMatchPlayer1(): MutableList<GameObject> {
    return mutableListOf<GameObject>(
        BackgroundGameObject(),
        Player1GameObject(true),
        Player2GameObject(false)
    )
}

fun createMatchPlayer2(): MutableList<GameObject> {
    return mutableListOf<GameObject>(
        BackgroundGameObject(),
        Player1GameObject(false),
        Player2GameObject(true)
    )
}
