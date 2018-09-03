@file:JvmName("Lwjgl3Launcher")

package io.github.davidpartouche.scenemanagement2demo.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import io.github.davidpartouche.scenemanagement2demo.SceneManagement2Demo

/** Launches the desktop (LWJGL3) application. */
fun main(args: Array<String>) {
    Lwjgl3Application(SceneManagement2Demo(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("SceneManagement2Demo")
        setWindowedMode(480, 320)
        setResizable(false)
        setWindowIcon(*(arrayOf(128, 64, 32, 16).map { "libgdx$it.png" }.toTypedArray()))
    })
}
