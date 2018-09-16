package codes.michael.gameoflife.ui.game

import codes.michael.gameoflife.BORDER_WIDTH
import codes.michael.gameoflife.CELL_SIZE
import codes.michael.gameoflife.service.GameService
import java.awt.GridLayout
import javax.swing.JPanel

/**
 * Extension of a JPanel that is used to hold the grid to visualize the simulation.
 */
class GamePanel(val rows: Int, val cols: Int, val gameService: GameService) : JPanel() {
  private val grid: Array<Array<Cell>>

  init {
    layout = GridLayout(rows, cols)

    //Initialize a grid of cells
    grid = Array(rows, { r ->
      Array(cols) { c ->
        Cell(r, c, gameService)
      }
    })

    //Take each of the cells and add its JLabel to our panel
    grid.forEach { row ->
      row.forEach { cell ->
        //Add the cell's label to the frame
        add(cell.jLabel)
      }
    }
  }

  /**
   * Calculate the width of the grid using our known parameters of the JLabels or the JPanel preferred size, whichever
   * is larger.
   */
  fun getCalculatedWidth(): Int {
    val guessedGridSize = (CELL_SIZE * cols) + (cols * BORDER_WIDTH * 2)
    val gridPreferredSize = preferredSize.width

    return if (guessedGridSize > gridPreferredSize) {
      guessedGridSize
    }
    else {
      gridPreferredSize
    }
  }

  /**
   * Calculate the height of the grid using our known parameters of the JLabels or the JPanel preferred size, whichever
   * is larger.
   */
  fun getCalculatedHeight(): Int {
    val guessedGridSize = (CELL_SIZE * rows) + (rows * BORDER_WIDTH * 2)
    val gridPreferredSize = preferredSize.height

    return if (guessedGridSize > gridPreferredSize) {
      guessedGridSize
    }
    else {
      gridPreferredSize
    }
  }

  fun update(values: Array<Array<Boolean>>) {
    for (row in 0..(rows - 1)) {
      for (col in 0..(cols - 1)) {
        grid[row][col].alive = values[row][col]
      }
    }
  }

}