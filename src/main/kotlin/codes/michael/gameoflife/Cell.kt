package codes.michael.gameoflife

import java.awt.Color
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.BorderFactory
import javax.swing.JLabel


class Cell {
  val cellSize = CELL_SIZE;

  val jlabel = JLabel()
  var alive = false
    set(newVal) {
      field = newVal
      updateColor()
    }

  init {
    alive = false
    jlabel.setSize(cellSize, cellSize)
    jlabel.isVisible = true
    jlabel.border = BorderFactory.createLineBorder(Color.GRAY, BORDER_WIDTH)
    jlabel.isOpaque = true
    jlabel.text = " "

    jlabel.addMouseListener(object : MouseAdapter() {
      override fun mouseClicked(e: MouseEvent) {
        alive = !alive
        updateColor()
      }
    })
  }

  fun updateColor() {
    jlabel.background = when (alive) {
      true -> Color.CYAN
      false -> Color.LIGHT_GRAY
    }
  }
}