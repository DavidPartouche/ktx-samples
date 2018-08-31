package io.github.davidpartouche.multitouchdemo;

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.*
import ktx.graphics.use

class MultitouchDemo : KtxApplicationAdapter, KtxInputAdapter {

    private lateinit var batch: SpriteBatch
    private lateinit var font: BitmapFont
    private lateinit var glyphLayout: GlyphLayout
    private lateinit var message: String
    private val width by lazy {
        Gdx.graphics.width
    }
    private val height by lazy {
        Gdx.graphics.height
    }

    data class TouchInfo(var touchX: Int = 0, var touchY: Int = 0, var isTouched: Boolean = false)

    private val touches = hashMapOf<Int, TouchInfo>()

    override fun create() {
        batch = SpriteBatch()
        font = BitmapFont(Gdx.files.internal("arial-15.fnt"))
        font.color = Color.RED
        glyphLayout = GlyphLayout()

        Gdx.input.inputProcessor = this

        (0 until 5).forEach {
            touches[it] = TouchInfo()
        }
    }

    override fun dispose() {
        batch.dispose()
        font.dispose()
    }

    override fun render() {
        clearScreen(1f, 1f, 1f)

        message = ""
        touches.filter { it.value.isTouched }.forEach {
            message += "Finger: ${it.key} touch at: ${it.value.touchX},${it.value.touchY}\n"
        }
        glyphLayout.setText(font, message)

        batch.use {
            font.draw(it, glyphLayout, width / 2f - glyphLayout.width / 2f,
                    height / 2f - glyphLayout.height / 2f)
        }
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        touches[pointer]?.touchX = screenX
        touches[pointer]?.touchY = screenY
        touches[pointer]?.isTouched = true

        return true
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        touches[pointer]?.isTouched = false
        return true
    }
}
