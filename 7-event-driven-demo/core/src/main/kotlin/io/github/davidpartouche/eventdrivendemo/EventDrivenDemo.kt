package io.github.davidpartouche.eventdrivendemo

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxApplicationAdapter
import ktx.app.KtxInputAdapter
import ktx.app.clearScreen
import ktx.graphics.use

class EventDrivenDemo : KtxApplicationAdapter, KtxInputAdapter {

    private lateinit var batch: SpriteBatch
    private lateinit var texture: Texture
    private lateinit var sprite: Sprite
    private var posX: Float = 0f
    private var posY: Float = 0f

    override fun create() {
        val w = Gdx.graphics.width
        val h = Gdx.graphics.height
        batch = SpriteBatch()

        texture = Texture(Gdx.files.internal("assets/0001.png"))
        sprite = Sprite(texture)
        posX = w / 2f - sprite.width / 2f
        posY = h / 2f - sprite.height / 2f
        sprite.setPosition(posX, posY)

        Gdx.input.inputProcessor = this
    }

    override fun dispose() {
        batch.dispose()
        texture.dispose()
    }

    override fun render() {
        clearScreen(1f, 1f, 1f)

        sprite.setPosition(posX, posY)

        batch.use {
            sprite.draw(batch)
        }
    }

    override fun keyDown(keycode: Int): Boolean {
        val moveAmount = if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) 10f else 1f
        when (keycode) {
            Input.Keys.LEFT -> posX -= moveAmount
            Input.Keys.RIGHT -> posX += moveAmount
        }
        return true
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        if (button == Input.Buttons.LEFT) {
            posX = screenX - sprite.width / 2f
            posY = Gdx.graphics.height - screenY - sprite.height / 2f
        }

        if (button == Input.Buttons.RIGHT) {
            posX = Gdx.graphics.width / 2f - sprite.width / 2f
            posY = Gdx.graphics.height / 2f - sprite.height / 2f
        }

        return true
    }
}
