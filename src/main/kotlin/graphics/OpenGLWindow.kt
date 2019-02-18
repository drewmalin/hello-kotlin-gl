package graphics

import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWKeyCallback
import org.lwjgl.opengl.GL
import org.lwjgl.system.MemoryUtil
import java.lang.RuntimeException

class OpenGLWindow(private val width: Int,
                   private val height: Int,
                   private val title: String,
                   private val enableVSync: Boolean) : Window {

    private var keyCallback : GLFWKeyCallback? = null
    private var window : Long? = null

    init {
        GLFW.glfwDefaultWindowHints()
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE)
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE)

        /*
         * Instantiate OpenGL window
         */
        this.window = GLFW.glfwCreateWindow(
            this.width,
            this.height,
            this.title,
            MemoryUtil.NULL,
            MemoryUtil.NULL
        )
        if (this.window == MemoryUtil.NULL) {
            GLFW.glfwTerminate()
            throw RuntimeException("Failed to create OpenGL window")
        }

        /*
         * Center window on screen
         */
        val videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor())
        GLFW.glfwSetWindowPos(
            this.window!!,
            (videoMode.width() - this.width) / 2,
            (videoMode.height() - this.height) / 2
        )

        /*
         * Set window as context
         */
        GLFW.glfwMakeContextCurrent(this.window!!)
        if (this.enableVSync) {
            GLFW.glfwSwapInterval(1)
        }
        GL.createCapabilities()

        /*
         * Allow for 'escape' to onClose window
         */
        this.keyCallback = GLFW.glfwSetKeyCallback(this.window!!, object : GLFWKeyCallback() {
            override fun invoke(window: kotlin.Long, key: kotlin.Int, scancode: kotlin.Int, action: kotlin.Int, mods: kotlin.Int) {
                if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_PRESS) {
                    GLFW.glfwSetWindowShouldClose(window, GLFW.GLFW_TRUE)
                }
            }
        })

        /*
         * Show the window
         */
        GLFW.glfwShowWindow(this.window!!)
    }

    override fun isClosed(): Boolean {
        return GLFW.glfwWindowShouldClose(this.window!!) == GLFW.GLFW_TRUE
    }

    override fun update() {
        GLFW.glfwSwapBuffers(this.window!!)
        GLFW.glfwPollEvents()
    }

    override fun destroy() {
        GLFW.glfwDestroyWindow(this.window!!)
        this.keyCallback?.release()
    }
}