@file:JvmName("Lwjgl3Launcher")

package io.github.davidpartouche.pixmapdemo.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import io.github.davidpartouche.pixmapdemo.PixmapDemo

/** Launches the desktop (LWJGL3) application. */
fun main(args: Array<String>) {
    Lwjgl3Application(PixmapDemo(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("PixmapDemo")
        setWindowedMode(480, 320)
        setResizable(false)
        setWindowIcon(*(arrayOf(128, 64, 32, 16).map { "libgdx$it.png" }.toTypedArray()))
    })
}
