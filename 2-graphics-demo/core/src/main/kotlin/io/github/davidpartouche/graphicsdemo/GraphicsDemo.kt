package io.github.davidpartouche.graphicsdemo;

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxApplicationAdapter
import ktx.app.clearScreen
import ktx.graphics.use

class GraphicsDemo : KtxApplicationAdapter {
    private lateinit var batch: SpriteBatch
    private lateinit var texture: Texture
    private lateinit var sprite: Sprite

    override fun create() {
        batch = SpriteBatch()
        texture = Texture(Gdx.files.internal("data/jet.png"))
        sprite = Sprite(texture)
    }

    override fun render() {
        clearScreen(1f, 1f, 1f)
        batch.use {
            sprite.draw(batch)
        }
    }

    override fun dispose() {
        batch.dispose()
        texture.dispose()
    }
}
