package graphics

/**
 * Window serves as a canvas for rendering.
 */
interface Window {

    /**
     * isClosed returns true if the window is no longer able to be updated.
     */
    fun isClosed() : Boolean

    /**
     * update causes the window to be re-drawn.
     */
    fun update()

    /**
     * destroy causes the window to be shut down. The window may not be used
     * after it has been destroyed.
     */
    fun destroy()
}
