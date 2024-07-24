package socketTrench.imageLoader

import java.awt.image.BufferedImage
import java.io.IOException
import java.net.URL
import javax.imageio.ImageIO
import javax.swing.ImageIcon

internal class URLLoader {
    fun asURL(path: String): BufferedImage? {
        val imagePath = "/$path"
        val inputStream = this::class.java.getResourceAsStream(imagePath)
        return try {
            ImageIO.read(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}

fun loadImageFromPath(path: String): BufferedImage? {
    return URLLoader().asURL(path)
}
