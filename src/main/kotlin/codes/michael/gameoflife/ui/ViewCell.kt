package codes.michael.gameoflife.ui

import codes.michael.gameoflife.*
import codes.michael.gameoflife.service.GameService
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.BorderFactory
import javax.swing.JLabel


class ViewCell(val row: Int, val col: Int, gameService: GameService) {
  val jlabel = JLabel()
  var alive = false
    set(newVal) {
      field = newVal
      updateColor()
    }

  init {
    alive = false
    jlabel.setSize(CELL_SIZE, CELL_SIZE)
    jlabel.isVisible = true
    jlabel.border = BorderFactory.createLineBorder(BORDER_COLOR, BORDER_WIDTH)
    jlabel.isOpaque = true
    jlabel.text = " "

    jlabel.addMouseListener(object : MouseAdapter() {
      override fun mouseClicked(e: MouseEvent) {
        alive = !alive
        updateColor()

        gameService.updateValue(row, col, alive)
      }
    })
  }

  fun updateColor() {
    jlabel.background = when (alive) {
      true -> ALIVE_COLOR
      false -> DEAD_COLOR
    }
  }
}