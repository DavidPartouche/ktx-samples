package io.github.davidpartouche.pollinginputdemo

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxApplicationAdapter
import ktx.app.clearScreen
import ktx.graphics.use

class PollingInputDemo : KtxApplicationAdapter {

    private lateinit var batch: SpriteBatch
    private lateinit var texture: Texture
    private lateinit var sprite: Sprite

    override fun create() {
        val w = Gdx.graphics.width
        val h = Gdx.graphics.height
        batch = SpriteBatch()

        texture = Texture(Gdx.files.internal("assets/0001.png"))
        sprite = Sprite(texture)
        sprite.setPosition(w / 2f - sprite.width / 2f, h / 2f - sprite.height / 2f)
    }

    override fun dispose() {
        batch.dispose()
        texture.dispose()
    }

    override fun render() {
        clearScreen(1f, 1f, 1f)


        val isLeftCtrlKeyPressed = Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)
        val translate = when {
            isLeftCtrlKeyPressed && Gdx.input.isKeyPressed(Input.Keys.LEFT) -> -1f
            Gdx.input.isKeyPressed(Input.Keys.LEFT) -> -10f
            isLeftCtrlKeyPressed && Gdx.input.isKeyPressed(Input.Keys.RIGHT) -> 1f
            Gdx.input.isKeyPressed(Input.Keys.RIGHT) -> 10f
            else -> 0f
        }
        sprite.translateX(translate)

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            sprite.setPosition(Gdx.input.x - sprite.width / 2f,
                    Gdx.graphics.height - Gdx.input.y - sprite.height / 2f)
        }

        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            sprite.setPosition(Gdx.graphics.width / 2f - sprite.width / 2f,
                    Gdx.graphics.height / 2f - sprite.height / 2f)
        }

        batch.use {
            sprite.draw(batch)
        }
    }
}
