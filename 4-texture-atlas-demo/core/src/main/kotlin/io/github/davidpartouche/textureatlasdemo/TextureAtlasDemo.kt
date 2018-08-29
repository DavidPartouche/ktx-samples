package io.github.davidpartouche.textureatlasdemo

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import ktx.app.KtxApplicationAdapter
import ktx.app.clearScreen
import ktx.async.enableKtxCoroutines
import ktx.async.ktxAsync
import ktx.graphics.use

class TextureAtlasDemo : KtxApplicationAdapter {

    private lateinit var batch: SpriteBatch
    private lateinit var textureAtlas: TextureAtlas
    private lateinit var sprite: Sprite
    private var currentFrame = 1
    private var currentAtlasKey = "0001"

    override fun create() {
        enableKtxCoroutines(1)

        batch = SpriteBatch()
        textureAtlas = TextureAtlas(Gdx.files.internal("assets/spritesheet.atlas"))
        val region = textureAtlas.findRegion(currentAtlasKey)
        sprite = Sprite(region)
        sprite.setPosition(120f, 100f)
        sprite.scale(2.5f)

        // Create a async task that update frames by changing the texture region
        // looping through all the sprites
        ktxAsync {
            while (true) {
                asynchronous {
                    currentFrame = currentFrame.mod(20).inc()
                    currentAtlasKey = currentFrame.toString().padStart(4, '0')
                    sprite.setRegion(textureAtlas.findRegion(currentAtlasKey))
                }
                delay(1 / 60f)
            }
        }
    }

    override fun dispose() {
        batch.dispose()
        textureAtlas.dispose()
    }

    override fun render() {
        clearScreen(1f, 1f, 1f)
        batch.use {
            sprite.draw(batch)
        }
    }
}
