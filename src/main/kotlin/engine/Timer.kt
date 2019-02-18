package engine

/**
 * Timer records elapsed time and keeps track of how often it is called upon
 * to update -- this information is useful in calculating delta intervals
 * between updates, and the number of updates occurring each second.
 */
interface Timer {

    /**
     * getDeltaTime returns the number of seconds that have elapsed since
     * the previous call to update.
     */
    fun getDeltaTime() : Double

    /**
     * update saves the current delta time, causing future calls to
     * getDeltaTime to begin from the current moment.
     */
    fun update()

    /**
     * getUpdatesPerSecond returns the frequency with which the update
     * method is invoked.
     */
    fun getUpdatesPerSecond() : Double
}