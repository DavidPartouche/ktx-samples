package io.github.davidpartouche.pixmapdemo;

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxApplicationAdapter
import ktx.app.clearScreen
import ktx.graphics.use

class PixmapDemo : KtxApplicationAdapter {
    private lateinit var batch: SpriteBatch
    private lateinit var pixmap: Pixmap
    private lateinit var texture: Texture
    private lateinit var sprite: Sprite

    override fun create() {
        batch = SpriteBatch()

        // A Pixmap is basically a raw image in memory as repesented by pixels
        // We create one 256 wide, 128 height using 8 bytes for Red, Green, Blue and Alpha channels
        pixmap = Pixmap(256, 128, Pixmap.Format.RGBA8888)

        // Fill it red
        pixmap.setColor(Color.RED)
        pixmap.fill()

        //Draw two lines forming an X
        pixmap.setColor(Color.BLACK)
        pixmap.drawLine(0, 0, pixmap.width - 1, pixmap.height - 1)
        pixmap.drawLine(0, pixmap.height - 1, pixmap.width - 1, 0)

        //Draw a circle about the middle
        pixmap.setColor(Color.YELLOW)
        pixmap.drawCircle(pixmap.width / 2, pixmap.height / 2, pixmap.height / 2 - 1)

        texture = Texture(pixmap)

        //It's the textures responsibility now... get rid of the pixmap
        pixmap.dispose()

        sprite = Sprite(texture)
    }

    override fun dispose() {
        batch.dispose()
        texture.dispose()
    }

    override fun render() {
        clearScreen(1f, 1f, 1f)

        batch.use {
            sprite.setPosition(0f, 0f)
            sprite.draw(batch)
            sprite.setPosition(Gdx.graphics.width / 2f, Gdx.graphics.height / 2f)
            sprite.draw(batch)
        }
    }
}
