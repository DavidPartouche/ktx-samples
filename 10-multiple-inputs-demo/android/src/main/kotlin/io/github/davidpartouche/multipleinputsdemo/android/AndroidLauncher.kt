package io.github.davidpartouche.multipleinputsdemo.android

import android.os.Bundle

import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import io.github.davidpartouche.multipleinputsdemo.MultipleInputsDemo

/** Launches the Android application. */
class AndroidLauncher : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize(MultipleInputsDemo(), AndroidApplicationConfiguration())
    }
}
