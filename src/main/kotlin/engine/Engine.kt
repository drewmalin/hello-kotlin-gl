package engine

import graphics.Window
import java.lang.RuntimeException

/**
 * Engine contains the main loop for the game. All of the disparate components
 * which make up the engine (the timer, the window, etc.) are updated during the
 * main loop.
 */
abstract class Engine {

    /**
     * run triggers the main loop. This method will return only upon encountering
     * an uncaught exception, or upon receiving a request by the engine's window
     * to onClose.
     */
    fun run() {
        try {
            while (true) {
                if (getWindow().isClosed()) {
                    break
                }
                /*
                 * Calculate delta time
                 */
                val startDelta = getTimer().getDeltaTime()
                /*
                 * Handle input
                 */
                //TODO

                /*
                 * Render graphical assets
                 */
                //TODO

                /*
                 * Update the engine timer
                 */
                getTimer().update()

                /*
                 * Update the OpenGL window
                 */
                getWindow().update()

                /*
                 * Yield CPU if time allows
                 */
                syncFramesPerSecond()

                println("FPS: " + getTimer().getUpdatesPerSecond())
            }
        } finally {
            onClose()
        }
    }

    private fun syncFramesPerSecond() {
        val maxFrameInterval = 1.0 / getMaxFramesPerSecond()
        while (getTimer().getDeltaTime() < maxFrameInterval) {
            try {
                Thread.sleep(1)
            } catch (e: InterruptedException) {
                throw RuntimeException("")
            }
        }
    }

    /**
     * getWindow returns the engine's window
     */
    abstract fun getWindow() : Window

    /**
     * getTimer returns the engine's timer
     */
    abstract fun getTimer() : OpenGLTimer

    /**
     * getMaxFramesPerSecond returns the maximum number of frames allows to
     * be rendered per second. This value will be interpreted by the engine
     * as an update interval which, if not exceeded by a single update cycle,
     * will prompt the engine to yield CPU time until the interval elapses.
     */
    abstract fun getMaxFramesPerSecond(): Int

    /**
     * onClose is invoked upon shutdown of the engine, as a final step after
     * the last update cycle of the main loop.
     */
    abstract fun onClose()
}