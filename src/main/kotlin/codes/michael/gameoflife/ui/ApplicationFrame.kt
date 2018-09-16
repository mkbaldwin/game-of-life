package codes.michael.gameoflife.ui

import codes.michael.gameoflife.BORDER_WIDTH
import codes.michael.gameoflife.CELL_SIZE
import codes.michael.gameoflife.GRID_COLS
import codes.michael.gameoflife.GRID_ROWS
import codes.michael.gameoflife.service.GameService
import codes.michael.gameoflife.simulation.GameGrid
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import java.awt.BorderLayout
import java.awt.GridLayout
import javax.swing.*
import javax.swing.WindowConstants.EXIT_ON_CLOSE

class ApplicationFrame() {
  val gameGrid = GameGrid()
  val viewGrid = ViewGrid(GameService(gameGrid))

  var isStarted: Boolean = false

  val jframe: JFrame

  /**
   * Primary constructor for the class (initialize the JFrame)
   */
  init {
    jframe = JFrame("Game of Life")
    jframe.isResizable = false
    jframe.defaultCloseOperation = EXIT_ON_CLOSE

    jframe.layout = BorderLayout()

    val toolbar = createToolbar()
    val grid = createGrid()

    jframe.add(toolbar, BorderLayout.PAGE_START)
    jframe.add(grid, BorderLayout.CENTER)

    val width = calcFrameWidth(grid, toolbar)
    val height = calcFrameHeight(grid, toolbar)
    jframe.setSize(width, height)

    gameGrid.onUpdate {
      viewGrid.update(it)
    }

    jframe.isVisible = true
  }

  /**
   * Create the toolbar and populate with its elements.
   */
  fun createToolbar(): JToolBar {
    val toolbar = JToolBar()

    with(toolbar) {
      isFloatable = false
      isRollover = true

      add(createNextButton())
      add(createClearButton())
      add(createStartButton())
      add(createStopButton())
    }

    return toolbar
  }

  /**
   * Create the next button & its listener
   */
  fun createNextButton(): JButton {
    val btn = JButton("Next")

    btn.addActionListener({
      gameGrid.computeNextGeneration()
    })

    return btn
  }

  /**
   * Create the clear button & its listener
   */
  fun createClearButton(): JButton {
    val btn = JButton("Clear")

    btn.addActionListener({
      gameGrid.clear()
      isStarted = false
    })

    return btn
  }

  /**
   * Create the start button and its listener
   */
  fun createStartButton(): JButton {
    val btn = JButton("Start")

    btn.addActionListener({
      //Launch a coroutine to compute the next generation asynchronously
      isStarted = true
      launch {
        while (isStarted) {
          gameGrid.computeNextGeneration()
          delay(500)
        }
      }

    })

    return btn
  }

  /**
   * Create the start button and its listener
   */
  fun createStopButton(): JButton {
    val btn = JButton("Stop")

    btn.addActionListener({
      isStarted = false
    })

    return btn
  }

  /**
   * Create the grid for viewing the output
   */
  fun createGrid(): JPanel {
    val panel = JPanel()
    panel.layout = GridLayout(GRID_ROWS, GRID_COLS)

    //Loop through the rows in the grid
    viewGrid.grid.forEach { row ->
      //Then through the columns
      row.forEach { cell ->
        //Add the cell's label to the frame
        panel.add(cell.jlabel)
      }
    }

    return panel
  }

  /**
   * Calculate the size of the grid and toolbar to use as the basis for our JFrame width.
   */
  fun calcFrameWidth(grid: JComponent, toolbar: JComponent): Int {
    val guessedGridSize = (CELL_SIZE * GRID_COLS) + (GRID_COLS * BORDER_WIDTH * 2)
    val gridPreferredSize = grid.preferredSize.width

    val gridSizeToUse = if (guessedGridSize > gridPreferredSize) {
      guessedGridSize
    }
    else {
      gridPreferredSize
    }

    val toolbarSize = toolbar.preferredSize.width

    //Return the larger of the two widths
    return if (gridSizeToUse > toolbarSize) {
      gridSizeToUse
    }
    else {
      toolbarSize
    }
  }

  /**
   * Calculate the size of the grid and toolbar to use as the basis for our JFrame height.
   */
  fun calcFrameHeight(grid: JComponent, toolbar: JComponent): Int {
    val guessedGridSize = (CELL_SIZE * GRID_ROWS) + (GRID_ROWS * BORDER_WIDTH * 2)
    val gridPreferredSize = grid.preferredSize.height

    val gridSizeToUse = if (guessedGridSize > gridPreferredSize) {
      guessedGridSize
    }
    else {
      gridPreferredSize
    }

    val toolbarSize = toolbar.preferredSize.height

    return toolbarSize + gridSizeToUse
  }
}