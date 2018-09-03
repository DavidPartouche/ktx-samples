package io.github.davidpartouche.scenedemo

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.Touchable
import ktx.actors.KtxInputListener
import ktx.actors.plus
import ktx.app.KtxApplicationAdapter
import ktx.app.clearScreen

class SceneDemo : KtxApplicationAdapter {

    private class MyActor(val texture: Texture) : Actor() {

        private var actorX = 0f
        private var actorY = 0f
        private var hasStarted = false

        init {
            setBounds(actorX, actorY, texture.width.toFloat(), texture.height.toFloat())
            addListener(object : KtxInputListener() {
                override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                    (event.target as MyActor).hasStarted = true
                    return true
                }
            })
        }

        override fun draw(batch: Batch?, parentAlpha: Float) {
            batch?.draw(texture, actorX, actorY)
        }

        override fun act(delta: Float) {
            if (hasStarted) {
                actorX -= 5
            }
        }
    }

    private lateinit var texture: Texture
    private lateinit var stage: Stage

    override fun create() {
        stage = Stage()
        texture = Texture(Gdx.files.internal("jet.png"))
        Gdx.input.inputProcessor = stage

        val myActor = MyActor(texture)
        myActor.touchable = Touchable.enabled
        stage + myActor
    }

    override fun dispose() {
        stage.dispose()
        texture.dispose()
    }

    override fun render() {
        clearScreen(0f, 0f, 0f)
        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }
}
