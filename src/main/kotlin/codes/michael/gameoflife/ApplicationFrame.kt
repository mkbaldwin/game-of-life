package codes.michael.gameoflife

import java.awt.GridLayout
import javax.swing.JFrame
import javax.swing.WindowConstants.EXIT_ON_CLOSE

class ApplicationFrame() {
  val gameGrid = GameGrid()
  val jframe: JFrame

  // Primary constructor for the class (initialize the JFrame)
  init {
    jframe = JFrame("Game of Life")
    jframe.setSize(calcFrameWidth(), calcFrameHeight())
    jframe.isResizable = false
    jframe.defaultCloseOperation = EXIT_ON_CLOSE

    initGrid()

    //jframe.pack()
    jframe.isVisible = true

  }

  fun initGrid() {
    val grid = GridLayout(GRID_ROWS, GRID_COLS)

    //Loop through the rows in the grid
    gameGrid.grid.forEach { row ->
      //Then through the columns
      row.forEach { cell ->
        //Add the cell's label to the frame
        jframe.add(cell.jlabel)
      }
    }

    jframe.layout = grid
    jframe
  }

  fun calcFrameWidth() = (CELL_SIZE * GRID_COLS) + (GRID_COLS * BORDER_WIDTH * 2)

  fun calcFrameHeight() = (CELL_SIZE * GRID_ROWS) + (GRID_ROWS * BORDER_WIDTH * 2)
}