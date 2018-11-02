package com.example.demo.view

import javafx.scene.image.Image
import javafx.scene.image.WritableImage
import tornadofx.*

class MainView : View("I am a chicken") {

    private val image = Image("rooster.jpg")
    private val width = image.width.toInt()
    private val height = image.height.toInt()
    private val wImage = WritableImage(image.pixelReader, width, height)


    override val root = hbox {
        imageview(wImage).apply {
            /*makeDuller(wImage)
            makeDuller(wImage)
            makeDuller(wImage)*/
        }
        hboxConstraints {
            prefWidth = 640.0
            prefHeight = 427.0
        }
    }

    private fun makeDuller(image: WritableImage) {
        val pixelWriter = image.pixelWriter

        for (x in 0 until width) {
            for (y in 0 until height) {
                val color = image.pixelReader.getColor(x, y)
                pixelWriter.setColor(x, y, color.desaturate())
            }
        }
    }
}