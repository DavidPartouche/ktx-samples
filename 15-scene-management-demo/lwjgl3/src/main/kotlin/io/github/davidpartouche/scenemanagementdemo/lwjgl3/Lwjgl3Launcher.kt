@file:JvmName("Lwjgl3Launcher")

package io.github.davidpartouche.scenemanagementdemo.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import io.github.davidpartouche.scenemanagementdemo.SceneManagementDemo

/** Launches the desktop (LWJGL3) application. */
fun main(args: Array<String>) {
    Lwjgl3Application(SceneManagementDemo(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("SceneManagementDemo")
        setWindowedMode(480, 320)
        setResizable(false)
        setWindowIcon(*(arrayOf(128, 64, 32, 16).map { "libgdx$it.png" }.toTypedArray()))
    })
}
