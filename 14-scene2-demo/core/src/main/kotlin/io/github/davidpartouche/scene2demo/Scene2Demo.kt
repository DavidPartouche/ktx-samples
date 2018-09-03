package io.github.davidpartouche.scene2demo

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions.*
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import ktx.actors.parallelTo
import ktx.actors.plus
import ktx.actors.then
import ktx.app.KtxApplicationAdapter
import ktx.app.clearScreen

class Scene2Demo : KtxApplicationAdapter {

    private class MyActor: Actor() {
        private val texture = Texture(Gdx.files.internal("jet.png"))
        val started = false

        init {
            setBounds(x, y, texture.width.toFloat(), texture.height.toFloat())
        }

        override fun draw(batch: Batch?, parentAlpha: Float) {
            batch?.draw(texture, x, y, originX, originY, width, height, scaleX, scaleY, rotation, 0, 0,
                    texture.width, texture.height, false, false)
        }
    }

    private lateinit var stage: Stage

    override fun create() {
        stage = Stage()
        Gdx.input.inputProcessor = stage

        val myActor= MyActor()

        // If we want to run them in sequence
        val sequence = scaleTo(0.5f, 0.5f, 5f) then rotateTo(90f, 5f) then moveTo(300f, 0f, 5f)


        // If we want to run htem in parallel
//        val parallel = scaleTo(0.5f, 0.5f, 5f) parallelTo rotateTo(90f, 5f) parallelTo  moveTo(300f, 0f, 5f)

        myActor + sequence

        stage + myActor
    }

    override fun render() {
        clearScreen(0f, 0f, 0f)
        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }
}
