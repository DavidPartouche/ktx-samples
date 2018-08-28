package io.github.davidpartouche.textureatlasdemo

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.utils.Timer
import com.badlogic.gdx.utils.async.AsyncExecutor
import ktx.app.KtxApplicationAdapter
import ktx.app.clearScreen
import ktx.async.enableKtxCoroutines
import ktx.async.interval
import ktx.async.ktxAsync
import ktx.graphics.use

class TextureAtlasDemo : KtxApplicationAdapter {

    private lateinit var batch: SpriteBatch
    private lateinit var textureAtlas: TextureAtlas
    private lateinit var sprite: Sprite
    private var currentFrame = 1
    private var currentAtlasKey = "0001"

    override fun create() {
//        enableKtxCoroutines(1)

        batch = SpriteBatch()
        textureAtlas = TextureAtlas(Gdx.files.internal("assets/spritesheet.atlas"))
        val region = textureAtlas.findRegion(currentAtlasKey)
        sprite = Sprite(region)
        sprite.setPosition(120f, 100f)
        sprite.scale(2.5f)

        Timer.schedule(object : Timer.Task() {
            override fun run() {
                currentFrame++
                if (currentFrame > 20)
                    currentFrame = 1

                currentAtlasKey = currentFrame.toString().padStart(4, '0')
                sprite.setRegion(textureAtlas.findRegion(currentAtlasKey))
            }

        }, 0f, 1 / 30f)

        // TODO: Add the Ktx asynchronous code
//        ktxAsync {
//            asynchronous {
//                interval(0f, 1 / 30f) {
//                    currentFrame++
//                    if (currentFrame > 20)
//                        currentFrame = 1
//
//                    currentAtlasKey = currentFrame.toString().padStart(4, '0')
//                    sprite.setRegion(textureAtlas.findRegion(currentAtlasKey))
//                }
//            }
//        }
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
