package codes.michael.gameoflife.ui.game

import codes.michael.gameoflife.*
import codes.michael.gameoflife.service.GameService
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.BorderFactory
import javax.swing.JLabel


/**
 * Represents an individual cell in the visualization of the simulation. This basically just wraps a JLabel with some
 * additional functionality. The label is just used to provide a square of color that is clickable.
 */
class Cell(val row: Int, val col: Int, val gameService: GameService) {
  val jLabel = JLabel(" ")

  var alive = false
    set(newVal) {
      field = newVal

      //If we change the state from outside make sure the color gets updated
      updateColor()
    }

  /**
   * Handle mouse clicks of the cell. Need to update the value/color stored in this cell, as well as alert the
   * game service that the value changed.
   */
  private val mouseListener = object : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent?) {
      super.mouseClicked(e)

      alive = !alive
      updateColor()

      gameService.updateValue(row, col, alive)

      println("$row,$col,$alive")
    }
  }

  init {
    //Configure the label properties
    with(jLabel) {
      setSize(CELL_SIZE, CELL_SIZE)
      isVisible = true
      border = BorderFactory.createLineBorder(BORDER_COLOR, BORDER_WIDTH)
      isOpaque = true
      background = DEAD_COLOR
      toolTipText = "$row,$col"

      //Add a click listener to change the state when someone clicks on a cell
      addMouseListener(mouseListener)
    }
  }

  /**
   * Update the label color based on the alive state.
   */
  private fun updateColor() {
    jLabel.background = when (alive) {
      true -> ALIVE_COLOR
      false -> DEAD_COLOR
    }
  }
}