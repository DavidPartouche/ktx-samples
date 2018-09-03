package io.github.davidpartouche.scenemanagementdemo

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.*
import com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo
import com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy
import ktx.actors.parallelTo
import ktx.actors.plus
import ktx.app.KtxApplicationAdapter
import ktx.app.clearScreen

class SceneManagementDemo : KtxApplicationAdapter {

    private lateinit var stage: Stage
    private lateinit var group: Group

    override fun create() {
        stage = Stage()
        val jetTexture = TextureRegion(Texture("jet.png"))
        val flameTexture = TextureRegion(Texture("flame.png"))

        val jet = object : Actor() {
            override fun draw(batch: Batch?, parentAlpha: Float) {
                batch?.draw(jetTexture, x, y, originX, originY, width, height, scaleX, scaleY, rotation)
            }
        }
        jet.setBounds(jet.x, jet.y, jetTexture.regionWidth.toFloat(), jetTexture.regionHeight.toFloat())

        val flame = object : Actor() {
            override fun draw(batch: Batch?, parentAlpha: Float) {
                batch?.draw(flameTexture, x, y, originX, originY, width, height, scaleX, scaleY, rotation)
            }
        }
        flame.setBounds(0f, 0f, flameTexture.regionWidth.toFloat(), flameTexture.regionHeight.toFloat())
        flame.setPosition(jet.width - 25f, 25f)

        group = Group()
        group + jet
        group + flame

        val sequence = moveTo(200f, 0f, 5f) parallelTo rotateBy(90f, 5f)
        group + sequence

        stage + group
    }

    override fun dispose() {
        stage.dispose()
    }

    override fun render() {
        clearScreen(0f, 0f, 0f)
        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }
}
