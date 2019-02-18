package engine

import org.lwjgl.glfw.GLFW

class OpenGLTimer : Timer {

    private var lastCheckedTimeSeconds: Double = 0.0
    private var updatesPerSecond: Double = 0.0

    override fun getDeltaTime(): Double {
        val currentTime = getCurrentTimeSeconds()
        return currentTime - lastCheckedTimeSeconds
    }

    override fun update() {
        val currentTime = getCurrentTimeSeconds()
        this.updatesPerSecond = 1 / (currentTime - lastCheckedTimeSeconds)
        this.lastCheckedTimeSeconds = currentTime
    }

    override fun getUpdatesPerSecond() : Double {
        return this.updatesPerSecond
    }

    private fun getCurrentTimeSeconds() = GLFW.glfwGetTime()
}