package io.github.davidpartouche.camerademo

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.input.GestureDetector
import ktx.app.KtxApplicationAdapter
import ktx.app.clearScreen
import ktx.graphics.use

class CameraDemo : KtxApplicationAdapter, GestureDetector.GestureAdapter() {

    private lateinit var camera: OrthographicCamera
    private lateinit var batch: SpriteBatch
    private lateinit var texture: Texture
    private lateinit var sprite: Sprite

    override fun create() {
        camera = OrthographicCamera(1280f, 720f)
        batch = SpriteBatch()
        texture = Texture(Gdx.files.internal("toronto2048.jpg"))
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)

        sprite = Sprite(texture)
        sprite.setOrigin(0f, 0f)
        sprite.setPosition(-sprite.width / 2f, -sprite.height / 2f)

        Gdx.input.inputProcessor = GestureDetector(this)
    }

    override fun dispose() {
        batch.dispose()
        texture.dispose()
    }

    override fun render() {
        clearScreen(1f, 1f, 1f)

        batch.projectionMatrix = camera.combined
        batch.use {
            sprite.draw(it)
        }
    }

    override fun pan(x: Float, y: Float, deltaX: Float, deltaY: Float): Boolean {
        camera.translate(-deltaX, deltaY)
        camera.update()
        return false
    }
}
