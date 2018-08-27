@file:JvmName("Lwjgl3Launcher")

package io.github.davidpartouche.graphicsdemo.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import io.github.davidpartouche.graphicsdemo.GraphicsDemo

/** Launches the desktop (LWJGL3) application. */
fun main(args: Array<String>) {
    Lwjgl3Application(GraphicsDemo(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("GraphicsDemo")
        setWindowedMode(480, 320)
        setResizable(false)
        setWindowIcon(*(arrayOf(128, 64, 32, 16).map { "libgdx$it.png" }.toTypedArray()))
    })
}
