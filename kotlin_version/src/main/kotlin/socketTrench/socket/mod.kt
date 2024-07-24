package socketTrench.socket

import java.io.IOException
import java.io.PrintStream
import java.net.ServerSocket
import java.net.Socket
import java.util.*
import java.util.logging.Level
import java.util.logging.Logger
import java.net.ConnectException
import java.net.BindException

interface SocketManager {
    fun handleMessage(message: String)
}

interface SocketConnection {
    fun setManager(manager: SocketManager): SocketConnection
    fun receive(message: String)
    fun send(message: String): SocketConnection
    fun close()
}

object SocketConstants {
    const val PORT = 54321
}

object SocketMessages {
    const val NO_SUCH_ELEMENT = "NO_SUCH_ELEMENT"
    const val CONN_SERVER = "CONN_SERVER"
    const val CONN_CLIENT = "CONN_CLIENT"
    const val CONN_REFUSED = "CONN_REFUSED"
    const val PORT_IN_USE = "PORT_IN_USE"
}

class SocketService {
    var manager: SocketManager? = null
    private var input: Scanner? = null
    private var output: PrintStream? = null
    private var socket: Socket? = null
    private var serverSocket: ServerSocket? = null
    private var isOpen = true

    fun setParams(
        input: Scanner,
        output: PrintStream,
        socket: Socket,
        serverSocket: ServerSocket?
    ) {
        this.input = input
        this.output = output
        this.socket = socket
        this.serverSocket = serverSocket
        startObserving()
    }

    private fun startObserving() {
        val input = this.input
        val output = this.output
        val socket = this.socket
        val serverSocket = this.serverSocket
        val manager = this.manager
        if(
            input == null ||
            output == null ||
            socket == null ||
            manager == null
        ) {
            return
        }
        manager.handleMessage(
            if (serverSocket != null
            ) SocketMessages.CONN_SERVER
            else SocketMessages.CONN_CLIENT
        )
        Thread {
            try {
                while (isOpen) {
                    receive(input.nextLine())
                }
            } catch (exception: NoSuchElementException) {
                manager.handleMessage(SocketMessages.NO_SUCH_ELEMENT)
            }
            try {
                input.close()
                output.close()
                socket.close()
                serverSocket?.close()
            } catch (exception: IOException) {
                Logger.getLogger("SocketService").log(Level.SEVERE, null, exception)
            }
        }.start()
    }

    fun receive(message: String?) {
        Logger.getLogger("SocketService").log(Level.INFO, message)
        if (message == null) {
            return
        }
        if (message == "") {
            return
        }
        val manager = this.manager
        manager?.handleMessage(message)
    }

    fun send(message: String) {
        if (output != null) {
            output!!.println(message)
        }
    }

    fun close() {
        isOpen = false
    }
}

class SocketClient(private val service: SocketService, private val ip: String) : SocketConnection {
    init {
        Thread {
            try {
                val socket = Socket(ip, SocketConstants.PORT)
                val output = PrintStream(socket.getOutputStream())
                val input = Scanner(socket.getInputStream())
                service.setParams(input, output, socket, null)
            } catch (exception: ConnectException) {
                service.manager?.handleMessage(SocketMessages.CONN_REFUSED)
            } catch (exception: IOException) {
                Logger.getLogger("SocketClient").log(Level.SEVERE, null, exception)
            }
        }.start()
    }

    override fun setManager(manager: SocketManager): SocketClient {
        service.manager = manager
        return this
    }

    override fun receive(message: String) {
        service.receive(message)
    }

    override fun send(message: String): SocketClient {
        service.send(message)
        return this
    }

    override fun close() {
        service.close()
    }
}

class SocketServer(private val service: SocketService) : SocketConnection {
    init {
        Thread {
            try {
                val socketServer = ServerSocket(SocketConstants.PORT)
                val socket = socketServer.accept()
                val output = PrintStream(socket.getOutputStream())
                val input = Scanner(socket.getInputStream())
                service.setParams(input, output, socket, socketServer)
            } catch (exception: BindException) {
                service.manager?.handleMessage(SocketMessages.PORT_IN_USE)
            } catch (exception: IOException) {
                Logger.getLogger("SocketServer").log(Level.SEVERE, null, exception)
            }
        }.start()
    }

    override fun setManager(manager: SocketManager): SocketServer {
        service.manager = manager
        return this
    }

    override fun receive(message: String) {
        service.receive(message)
    }

    override fun send(message: String): SocketServer {
        service.send(message)
        return this
    }

    override fun close() {
        service.close()
    }
}

internal var socketConnection: SocketConnection? = null

fun createConnection(socket: SocketConnection) {
    socketConnection = socket
}

fun getConnection(): SocketConnection? {
    return socketConnection
}

fun closeConnection() {
    if (socketConnection != null) {
        socketConnection!!.close()
        socketConnection = null
    }
}
