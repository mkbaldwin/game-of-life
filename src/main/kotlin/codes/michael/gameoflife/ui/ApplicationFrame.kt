package codes.michael.gameoflife.ui

import codes.michael.gameoflife.GRID_COLS
import codes.michael.gameoflife.GRID_ROWS
import codes.michael.gameoflife.service.GameService
import codes.michael.gameoflife.simulation.LifeSimulation
import codes.michael.gameoflife.ui.game.GamePanel
import java.awt.BorderLayout
import javax.swing.JFrame
import javax.swing.JTextField
import javax.swing.WindowConstants.EXIT_ON_CLOSE

class ApplicationFrame() {
  val lifeSimulation = LifeSimulation()
  val gameService = GameService(lifeSimulation)
  val gamePanel = GamePanel(GRID_ROWS, GRID_COLS, gameService)
  val toolbar = ControlsToolbar(gameService)

  //val viewGrid = ViewGrid(GameService(gameGrid))

  //var isStarted: Boolean = false

  val jframe: JFrame
  var generationCountField: JTextField? = null
 // var generationCount = 0

  /**
   * Primary constructor for the class (initialize the JFrame)
   */
  init {
    jframe = JFrame("Game of Life")
    jframe.isResizable = false
    jframe.defaultCloseOperation = EXIT_ON_CLOSE

    jframe.layout = BorderLayout()


    jframe.add(toolbar, BorderLayout.PAGE_START)
    jframe.add(gamePanel, BorderLayout.CENTER)

    val width = calcFrameWidth(gamePanel, toolbar)
    val height = calcFrameHeight(gamePanel, toolbar)
    jframe.setSize(width, height)

    gameService.addOnUpdateFunction {
      gamePanel.update(it)
    }
    /*gameGrid.onUpdate {
      viewGrid.update(it)
    }*/

    jframe.isVisible = true
  }

  /**
   * Create the toolbar and populate with its elements.
   */
  /*fun createToolbar(): JToolBar {
    val toolbar = JToolBar()

    with(toolbar) {
      isFloatable = false
      isRollover = true

      add(createNextButton())
      add(createClearButton())
      add(createStartButton())
      add(createStopButton())
      createGenerationCounter(this)
    }

    return toolbar
  }*/

/*
  fun createGenerationCounter(toolbar: JToolBar)  {
    toolbar.add(JLabel("Generation: "))
    generationCountField = JTextField()
    generationCountField!!.text = generationCount.toString()
    toolbar.add(generationCountField)
  }
*/

  /**
   * Create the grid for viewing the output
   */
 /* fun createGrid(): JPanel {
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
  }*/

  /**
   * Calculate the size of the grid and toolbar to use as the basis for our JFrame width.
   */
  fun calcFrameWidth(grid: GamePanel, toolbar: ControlsToolbar): Int {
    val gridSizeToUse = grid.getCalculatedWidth()
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
  fun calcFrameHeight(grid: GamePanel, toolbar: ControlsToolbar): Int {
    val gridSizeToUse = grid.getCalculatedHeight()
    val toolbarSize = toolbar.preferredSize.height

    return toolbarSize + gridSizeToUse
  }

}