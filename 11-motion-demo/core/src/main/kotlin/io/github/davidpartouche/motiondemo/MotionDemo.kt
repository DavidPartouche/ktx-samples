package io.github.davidpartouche.motiondemo

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxApplicationAdapter
import ktx.app.clearScreen
import ktx.graphics.use

class MotionDemo : KtxApplicationAdapter {

    private lateinit var batch: SpriteBatch
    private lateinit var font: BitmapFont
    private var message = "Do something already!"
    private var highestY = 0f

    override fun create() {
        batch = SpriteBatch()
        font = BitmapFont(Gdx.files.internal("arial-15.fnt"), false)
        font.color = Color.RED
    }

    override fun dispose() {
        batch.dispose()
        font.dispose()
    }

    override fun render() {
        clearScreen(1f, 1f, 1f)

        val w = Gdx.graphics.width
        val h = Gdx.graphics.height

        val deviceAngle = Gdx.input.rotation
        val orientation = Gdx.input.nativeOrientation
        val accelY = Gdx.input.accelerometerY
        if (accelY > highestY)
            highestY = accelY

        message = "Device rotated to: $deviceAngle degrees.\n"
        message += "Device orientation is ${orientation.name}\n"
        message += "Device resolution: $w,$h\n"
        message += "Y axis accel: $accelY\n"
        message += "Highest Y value: $highestY\n"

        if (Gdx.input.isPeripheralAvailable(Input.Peripheral.Vibrator)) {
            if (accelY > 7) {
                Gdx.input.vibrate(100)
            }
        }

        if (Gdx.input.isPeripheralAvailable(Input.Peripheral.Compass)) {
            message += "Azimuth: ${Gdx.input.azimuth}\n"
            message += "Pitch: ${Gdx.input.pitch}\n"
            message += "Roll: ${Gdx.input.roll}\n"
        }
        else {
            message += "No compass available\n"
        }

        batch.use {
            font.draw(it, message, 0f, h.toFloat())
        }
    }

    // When the device is rotated, the sprite batch gets invalidated, and needs to be recreated.
    override fun resize(width: Int, height: Int) {
        batch.dispose()
        batch = SpriteBatch()
        Gdx.app.log("MJF", "Resolution changed: $width,$height")
    }
}
