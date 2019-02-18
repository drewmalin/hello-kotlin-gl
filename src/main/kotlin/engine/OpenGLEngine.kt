package engine

import graphics.OpenGLWindow
import graphics.Window
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback
import java.lang.IllegalStateException

data class Config(
    val windowWidth: Int,
    val windowHeight: Int,
    val windowName: String = "",
    val enableVSync: Boolean = false,
    val maxFramesPerSecond: Int = 120
)

class OpenGLEngine(private val config : Config) : Engine() {

    private var errCallback : GLFWErrorCallback? = null

    private var window : OpenGLWindow? = null
    private var timer : OpenGLTimer? = null

    init{
        this.errCallback = GLFW.glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err))

        if (GLFW.glfwInit() != GLFW.GLFW_TRUE) {
            throw IllegalStateException("")
        }

        this.timer = OpenGLTimer()
        this.window = OpenGLWindow(
            this.config.windowWidth,
            this.config.windowHeight,
            this.config.windowName,
            this.config.enableVSync
        )
    }

    override fun getTimer(): OpenGLTimer {
        return this.timer!!
    }

    override fun getWindow(): Window {
        return this.window!!
    }

    override fun getMaxFramesPerSecond(): Int {
        return this.config.maxFramesPerSecond
    }

    override fun onClose() {
        this.window?.destroy()
        GLFW.glfwTerminate()
        this.errCallback?.release()
    }
}