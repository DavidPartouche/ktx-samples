@file:JvmName("Lwjgl3Launcher")

package io.github.davidpartouche.eventdrivendemo.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import io.github.davidpartouche.eventdrivendemo.EventDrivenDemo

/** Launches the desktop (LWJGL3) application. */
fun main(args: Array<String>) {
    Lwjgl3Application(EventDrivenDemo(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("EventDrivenDemo")
        setWindowedMode(480, 320)
        setResizable(false)
        setWindowIcon(*(arrayOf(128, 64, 32, 16).map { "libgdx$it.png" }.toTypedArray()))
    })
}
