package socketTrench.engine

import socketTrench.app.Screen
import socketTrench.events.DefaultDispatcher
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.image.BufferedImage
import java.util.*
import kotlin.concurrent.fixedRateTimer

class EngineKeyAdapter(private val gameObjects: MutableList<GameObject>, private val engineState: EngineState) :
    KeyListener {
    private var keys: MutableSet<Int> = HashSet()

    override fun keyTyped(event: KeyEvent) {}

    override fun keyPressed(event: KeyEvent) {
        val keyCode: Int = event.keyCode
        if (keys.contains(keyCode)) {
            return
        }
        for (gameObject in gameObjects) {
            val inputHandler = gameObject.getInputHandler()
            inputHandler?.onKeyPressed(keyCode, engineState)
        }
    }

    override fun keyReleased(event: KeyEvent) {
        val keyCode: Int = event.keyCode
        keys.remove(keyCode)
        for (gameObject in gameObjects) {
            val inputHandler = gameObject.getInputHandler()
            inputHandler?.onKeyReleased(keyCode)
        }
    }
}

abstract class DefaultGameObject : GameObject {
    private var body: BoxDim? = null
    private var collider: BoxDim? = null
    private var texture: BufferedImage? = null
    private var force: Point? = null
    private var bounds: BoxPos? = null
    private var inputHandler: InputHandler? = null

    override fun getBody(): BoxDim? {
        return body
    }

    override fun getCollider(): BoxDim? {
        return collider
    }

    override fun getTexture(): BufferedImage? {
        return texture
    }

    override fun getForce(): Point? {
        return force
    }

    override fun getBounds(): BoxPos? {
        return bounds
    }

    override fun getInputHandler(): InputHandler? {
        return inputHandler
    }

    override fun setBody(body: BoxDim) {
        this.body = body
    }

    override fun setCollider(collider: BoxDim) {
        this.collider = collider
    }

    override fun setTexture(texture: BufferedImage?) {
        this.texture = texture
    }

    override fun setForce(force: Point) {
        this.force = force
    }

    override fun setBounds(bounds: BoxPos) {
        this.bounds = bounds
    }

    override fun setInputHandler(inputHandler: InputHandler) {
        this.inputHandler = inputHandler
    }

    override fun onLeaveScreen(engineState: EngineState) {
    }

    override fun onCollideWith(other: GameObject, engineState: EngineState) {
    }
}

data class BoxDim(var x: Int, var y: Int, var w: Int, var h: Int)
data class BoxPos(var x1: Int, var y1: Int, var x2: Int, var y2: Int)
data class Point(var x: Int, var y: Int)

interface GameObject {
    fun getBody(): BoxDim?
    fun getCollider(): BoxDim?
    fun getTexture(): BufferedImage?
    fun getForce(): Point?
    fun getBounds(): BoxPos?
    fun getInputHandler(): InputHandler?
    fun setBody(body: BoxDim)
    fun setCollider(collider: BoxDim)
    fun setTexture(texture: BufferedImage?)
    fun setForce(force: Point)
    fun setBounds(bounds: BoxPos)
    fun setInputHandler(inputHandler: InputHandler)
    fun onLeaveScreen(engineState: EngineState)
    fun onCollideWith(other: GameObject, engineState: EngineState)
}

interface InputHandler {
    fun onKeyPressed(keyCode: Int, engineState: EngineState)
    fun onKeyReleased(keyCode: Int)
}

class EngineRenderer(private val gameObjects: MutableList<GameObject>) {
    private val buffer = BufferedImage(Screen.WIDTH, Screen.HEIGHT, BufferedImage.TYPE_INT_RGB)

    fun render(graphics: Graphics) {
        val bufferGraphics: Graphics2D = buffer.createGraphics()
        for (gameObject in gameObjects) {
            val texture = gameObject.getTexture()
            val body = gameObject.getBody()
            if (texture != null && body != null) {
                bufferGraphics.drawImage(texture, body.x, body.y, body.w, body.h, null)
            }
        }
        graphics.drawImage(buffer, 0, 0, null)
        bufferGraphics.dispose()
    }
}

class EngineState(private val gameObjects: MutableList<GameObject>) {
    private val createList = mutableListOf<GameObject>()
    private val deleteList = mutableListOf<GameObject>()

    fun create(gameObject: GameObject) {
        createList.add(gameObject)
    }

    fun destroy(gameObject: GameObject) {
        deleteList.add(gameObject)
    }

    fun apply() {
        for (gameObject in deleteList) {
            gameObjects.remove(gameObject)
        }
        deleteList.clear()
        for (gameObject in createList) {
            gameObjects.add(gameObject)
        }
        createList.clear()
    }
}

internal fun applyPhysics(gameObject: GameObject) {
    val body = gameObject.getBody()
    val force = gameObject.getForce()
    val bounds = gameObject.getBounds()
    if (body == null) {
        return
    }
    if (force == null) {
        return
    }
    body.x += force.x
    body.y += force.y
    if (bounds == null) {
        return
    }
    if (body.x < bounds.x1) {
        body.x = bounds.x1
    }
    if (body.x > bounds.x2 - body.w) {
        body.x = bounds.x2 - body.w
    }
    if (body.y < bounds.y1) {
        body.y = bounds.y1
    }
    if (body.y > bounds.y2 - body.h) {
        body.y = bounds.y2 - body.h
    }
}

internal fun applyCollisions(gameObjects: MutableList<GameObject>, engineState: EngineState) {
    for (gameObject in gameObjects) {
        val collider = gameObject.getCollider()
        if (collider != null) {
            for (other in gameObjects) {
                if (gameObject != other) {
                    val otherBody = other.getCollider()
                    if (otherBody != null) {
                        if (
                            collider.x < otherBody.x + otherBody.w &&
                            collider.x + collider.w > otherBody.x &&
                            collider.y < otherBody.y + otherBody.h &&
                            collider.y + collider.h > otherBody.y
                        ) {
                            gameObject.onCollideWith(other, engineState)
                        }
                    }
                }
            }
        }
    }
}

object EngineMessages {
    const val RENDER = "RENDER"
}

internal object EngineConstants {
    const val FPS = 60
}

class EngineManager(
    private val gameObjects: MutableList<GameObject>,
    private val engineState: EngineState,
    private val dispatcher: DefaultDispatcher
) {
    private lateinit var timer: Timer

    fun onInit() {
        timer = fixedRateTimer("timer", initialDelay = 0, period = (1000 / EngineConstants.FPS).toLong()) {
            onUpdate()
        }
    }

    private fun onUpdate() {
        engineState.apply()
        for (gameObject in gameObjects) {
            applyPhysics(gameObject)
        }
        for (gameObject in gameObjects) {
            val body = gameObject.getBody()
            if (body != null) {
                if (
                    body.x + body.w < 0 ||
                    body.y + body.h < 0 ||
                    body.x > Screen.WIDTH ||
                    body.y > Screen.HEIGHT
                ) {
                    gameObject.onLeaveScreen(engineState)
                }
            }
        }
        applyCollisions(gameObjects, engineState)
        dispatcher.dispatch(EngineMessages.RENDER)
    }
}
