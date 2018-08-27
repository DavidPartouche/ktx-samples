package io.github.davidpartouche.helloworld;

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxApplicationAdapter
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.graphics.use

class FirstScreen : KtxScreen {
    private val image = Texture("ktx-logo.png")
    private val batch = SpriteBatch()

    override fun render(delta: Float) {
        clearScreen(0.8f, 0.8f, 0.8f)
        batch.use {
            it.draw(image, 47.5f, 140f)
        }
    }

    override fun dispose() {
        image.dispose()
        batch.dispose()
    }
}

class HelloWorld : KtxApplicationAdapter {
    lateinit var batch: SpriteBatch
    lateinit var font: BitmapFont

    override fun create() {
        batch =  SpriteBatch()
        font = BitmapFont()
        font.color = Color.RED
    }

    override fun render() {
        clearScreen(1f, 1f, 1f)

        batch.use {
            font.draw(batch, "Hello, World!", 200f, 200f)
        }
    }

    override fun dispose() {
        batch.dispose()
        font.dispose()
    }
}
