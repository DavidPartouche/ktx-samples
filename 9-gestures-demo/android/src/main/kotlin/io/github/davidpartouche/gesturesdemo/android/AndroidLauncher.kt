package io.github.davidpartouche.gesturesdemo.android

import android.os.Bundle

import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import io.github.davidpartouche.gesturesdemo.GesturesDemo

/** Launches the Android application. */
class AndroidLauncher : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize(GesturesDemo(), AndroidApplicationConfiguration())
    }
}
