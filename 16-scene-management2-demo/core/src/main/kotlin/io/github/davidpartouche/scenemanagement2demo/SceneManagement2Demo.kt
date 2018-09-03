package io.github.davidpartouche.scenemanagement2demo

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils.random
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import ktx.actors.plus
import ktx.app.KtxApplicationAdapter
import ktx.app.clearScreen
import ktx.log.debug

class SceneManagement2Demo : KtxApplicationAdapter {

    private class Jet(private val texture: TextureRegion) : Actor() {

        init {
            setBounds(x, y, texture.regionWidth.toFloat(), texture.regionHeight.toFloat())
            addListener(object : InputListener() {
                override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                    debug { "Touched + $name" }
                    isVisible = false
                    return true
                }
            })
        }

        // Implement the full form of draw() so we can handle rotation and scaling.
        override fun draw(batch: Batch?, parentAlpha: Float) {
            batch?.draw(texture, x, y, originX, originY, width, height, scaleX, scaleY, rotation)
        }

        // This hit() instead of checking against a bounding box, checks a bounding circle.
        override fun hit(x: Float, y: Float, touchable: Boolean): Actor? {
            // If this Actor is hidden or untouchable, it cant be hit
            if (!isVisible || !isTouchable) {
                return null
            }

            // Get centerpoint of bounding circle, also known as the center of the rect
            val centerX = width / 2.0
            val centerY = height / 2.0

            // Square roots are bad m'kay. In "real" code, simply square both sides for much speedy fastness
            // This however is the proper, unoptimized and easiest to grok equation for a hit within a circle
            // You could of course use LibGDX's Circle class instead.

            // Calculate radius of circle
            val radius = Math.sqrt(centerX * centerX + centerY * centerY)

            // And distance of point from the center of the circle
            val distance = Math.sqrt((centerX - x) * (centerX - x) + (centerY - y) * (centerY - y))

            // If the distance is less than the circle radius, it's a hit
            if (distance <= radius) return this

            // Otherwise, it isnt
            return null
        }
    }

    private val jets = mutableListOf<Jet>()
    private lateinit var stage: Stage

    override fun create() {
        stage = Stage()

        val jetTexture = TextureRegion(Texture("jet.png"))

        // Create 10 Jet objects at random on screen locations
        (0 until 10).forEach {
            jets.add(Jet(jetTexture))

            //Assign the position of the jet to a random value within the screen boundaries
            jets[it].setPosition(random.nextInt(Gdx.graphics.width - jets[it].width.toInt()).toFloat(),
                    random.nextInt(Gdx.graphics.height - jets[it].height.toInt()).toFloat())

            // Set the name of the Jet to it's index within the loop
            jets[it].name = "$it"

            // Add them to the stage
            stage + jets[it]
        }

        Gdx.input.inputProcessor = stage
    }

    override fun dispose() {
        stage.dispose()
    }

    override fun render() {
        clearScreen(0f, 0f, 0f)
        stage.act()
        stage.draw()
    }
}
