package io.github.davidpartouche.animationdemo;

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import ktx.app.KtxApplicationAdapter
import ktx.app.clearScreen
import ktx.graphics.use

class AnimationDemo : KtxApplicationAdapter {
    private lateinit var batch: SpriteBatch
    private lateinit var textureAtlas: TextureAtlas
    private lateinit var animation: Animation<TextureRegion>
    private var elapsedTime = 0f

    override fun create() {
        batch = SpriteBatch()
        textureAtlas = TextureAtlas(Gdx.files.internal("assets/spritesheet.atlas"))
        animation = Animation(1 / 15f, textureAtlas.regions)
    }

    override fun dispose() {
        batch.dispose()
        textureAtlas.dispose()
    }

    override fun render() {
        clearScreen(0f, 0f, 0f)

        batch.use {
            elapsedTime += Gdx.graphics.deltaTime
            batch.draw(animation.getKeyFrame(elapsedTime, true), 0f, 0f)
        }
    }
}
