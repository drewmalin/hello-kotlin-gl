package main

import engine.Config
import engine.OpenGLEngine

fun main() {
    val engine = OpenGLEngine(
        Config(
            windowWidth = 500,
            windowHeight = 500,
            windowName = "Hello",
            maxFramesPerSecond = 120
        )
    )
    engine.run()
}