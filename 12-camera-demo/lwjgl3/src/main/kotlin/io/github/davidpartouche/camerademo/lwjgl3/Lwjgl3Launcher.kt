@file:JvmName("Lwjgl3Launcher")

package io.github.davidpartouche.camerademo.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import io.github.davidpartouche.camerademo.CameraDemo

/** Launches the desktop (LWJGL3) application. */
fun main(args: Array<String>) {
    Lwjgl3Application(CameraDemo(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("CameraDemo")
        setWindowedMode(1280, 720)
        setResizable(false)
        setWindowIcon(*(arrayOf(128, 64, 32, 16).map { "libgdx$it.png" }.toTypedArray()))
    })
}
