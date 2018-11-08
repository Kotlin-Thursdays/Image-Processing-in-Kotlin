package com.example.demo.view

import javafx.scene.image.Image
import javafx.scene.image.PixelReader
import javafx.scene.image.PixelWriter
import javafx.scene.image.WritableImage
import javafx.scene.paint.Color
import tornadofx.*

class MainView : View("I am a chicken") {

    private val image = Image("rooster.jpg")
    private val monochromatic1 = Image("bw_image.png")
    private val monochromatic2 = Image("tree.png")
    private val width = monochromatic1.width.toInt()
    private val height = monochromatic1.height.toInt()
    private val wImage1 = WritableImage(monochromatic1.pixelReader, width, height)

    private val resultWriter = wImage1.pixelWriter


    override val root = hbox {
        imageview(wImage1).apply {
            orFilter(monochromatic1.pixelReader, monochromatic2.pixelReader)
        }
        hboxConstraints {
            prefWidth = 400.0
            prefHeight = 400.0
        }
    }

    fun orFilter(a: PixelReader, b: PixelReader): PixelWriter {
        for (x in 0 until width) {      // 0 -> (width-1)
            for (y in 0 until height) {
                resultWriter.setColor(x, y, or2(a.getColor(x, y), b.getColor(x, y)))
            }
        }
        return resultWriter
    }

    // or || - only 1 black to be true
    private fun or(a: Color, b: Color): Color {
        return if (a == Color.BLACK || b == Color.BLACK) Color.BLACK else Color.WHITE
    }

    private fun or2(a: Color, b: Color): Color {
        if (a == Color.BLACK || b == Color.BLACK) return Color.BLACK else return Color.WHITE
    }

    // and && - both black to be true
    private fun and(a: Color, b: Color): Color {
        return if (a == Color.BLACK && b == Color.BLACK) Color.BLACK else Color.WHITE
    }

    // not || return the opposite result
    private fun not(color: Color): Color {
        return if (color == Color.BLACK) Color.WHITE else Color.BLACK
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