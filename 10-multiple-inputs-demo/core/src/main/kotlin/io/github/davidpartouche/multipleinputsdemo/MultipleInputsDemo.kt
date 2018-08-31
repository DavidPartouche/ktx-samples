package io.github.davidpartouche.multipleinputsdemo

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.math.Vector2
import ktx.app.KtxApplicationAdapter
import ktx.app.KtxInputAdapter
import ktx.app.clearScreen
import ktx.graphics.use

class MultipleInputsDemo : KtxApplicationAdapter, GestureDetector.GestureAdapter(), KtxInputAdapter {

    private lateinit var batch: SpriteBatch
    private lateinit var font: BitmapFont
    private lateinit var glyphLayout: GlyphLayout
    private var message: String = "No gesture performed yet"
    private val width by lazy {
        Gdx.graphics.width
    }
    private val height by lazy {
        Gdx.graphics.height
    }

    override fun create() {
        batch = SpriteBatch()
        font = BitmapFont(Gdx.files.internal("arial-15.fnt"))
        font.color = Color.RED
        glyphLayout = GlyphLayout()

        val inputMultiplexer = InputMultiplexer()
        val gestureDetector = GestureDetector(this)
        inputMultiplexer.addProcessor(gestureDetector)
        inputMultiplexer.addProcessor(this)

        Gdx.input.inputProcessor = inputMultiplexer
    }

    override fun dispose() {
        batch.dispose()
        font.dispose()
    }

    override fun render() {
        clearScreen(1f, 1f, 1f)

        glyphLayout.setText(font, message)
        batch.use {
            font.draw(it, glyphLayout, width / 2f - glyphLayout.width / 2f,
                    height / 2f - glyphLayout.height / 2f)
        }


    }

    override fun tap(x: Float, y: Float, count: Int, button: Int): Boolean {
        message = "Tap performed, finger: $button"
        Gdx.app.log("INFO", message)
        return false
    }

    override fun longPress(x: Float, y: Float): Boolean {
        message = "Long press performed"
        Gdx.app.log("INFO", message)
        return true
    }

    override fun fling(velocityX: Float, velocityY: Float, button: Int): Boolean {
        message = "Fling performed, velocity: $velocityX,$velocityY"
        Gdx.app.log("INFO", message)
        return true
    }

    override fun pan(x: Float, y: Float, deltaX: Float, deltaY: Float): Boolean {
        message = "Pan performed, delta: $deltaX,$deltaY"
        Gdx.app.log("INFO", message)
        return true
    }

    override fun zoom(initialDistance: Float, distance: Float): Boolean {
        message = "Zoom performed, initial distance: $initialDistance, distance: $distance"
        Gdx.app.log("INFO", message)
        return true
    }

    override fun pinch(initialPointer1: Vector2?, initialPointer2: Vector2?, pointer1: Vector2?, pointer2: Vector2?): Boolean {
        message = "Pinch performed"
        Gdx.app.log("INFO", message)
        return true
    }

    override fun touchDown(x: Float, y: Float, pointer: Int, button: Int): Boolean {
        message = "touch down!"
        Gdx.app.log("INFO", message)
        return true
    }

    override fun keyDown(keycode: Int): Boolean {
        message = "Key Down"
        Gdx.app.log("INFO", message)
        return true
    }

    override fun keyUp(keycode: Int): Boolean {
        message = "Key Up"
        Gdx.app.log("INFO", message)
        return true
    }

    override fun keyTyped(character: Char): Boolean {
        message = "Key typed"
        Gdx.app.log("INFO", message)
        return true
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        message = "Touch Down"
        Gdx.app.log("INFO", message)
        return false
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        message = "Touch Up"
        Gdx.app.log("INFO", message)
        return false
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        message = "Touch Dragged"
        Gdx.app.log("INFO", message)
        return false
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        message = "Mouse Moved"
        Gdx.app.log("INFO", message)
        return false
    }

    override fun scrolled(amount: Int): Boolean {
        message = "Scrolled"
        Gdx.app.log("INFO", message)
        return false
    }
}

