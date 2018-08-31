package io.github.davidpartouche.multitouchdemo.android

import android.os.Bundle

import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import io.github.davidpartouche.multitouchdemo.MultitouchDemo

/** Launches the Android application. */
class AndroidLauncher : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize(MultitouchDemo(), AndroidApplicationConfiguration())
    }
}
