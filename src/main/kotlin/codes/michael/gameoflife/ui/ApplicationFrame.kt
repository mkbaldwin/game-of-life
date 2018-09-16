package codes.michael.gameoflife.ui

import codes.michael.gameoflife.GRID_COLS
import codes.michael.gameoflife.GRID_ROWS
import codes.michael.gameoflife.service.GameService
import codes.michael.gameoflife.ui.game.GamePanel
import java.awt.BorderLayout
import javax.swing.JFrame
import javax.swing.WindowConstants.EXIT_ON_CLOSE

class ApplicationFrame(gameService: GameService) {
  private val gamePanel = GamePanel(GRID_ROWS, GRID_COLS, gameService)
  private val toolBar = ControlsToolbar(gameService)
  private val statusBar = StatusBar()
  private val jframe = JFrame("Game of Life")

  /**
   * Primary constructor for the class (initialize the JFrame)
   */
  init {
    with(jframe) {
      isResizable = false
      defaultCloseOperation = EXIT_ON_CLOSE
      layout = BorderLayout()
      setSize(calcFrameWidth(), calcFrameHeight())

      add(toolBar, BorderLayout.NORTH)
      add(gamePanel, BorderLayout.CENTER)
      add(statusBar, BorderLayout.SOUTH)

      isVisible = true
    }

    // Register a listener for updates to the game state
    gameService.addGameStateListener {
      gamePanel.update(it)
    }

    //Register a listener for updates to the generation count
    gameService.addGenerationCountListener {
      statusBar.updateGenerationCount(it)
    }
  }

  /**
   * Calculate the size of the grid and toolBar to use as the basis for our JFrame width.
   */
  fun calcFrameWidth(): Int {
    val gamePanelSize = gamePanel.getCalculatedWidth()
    val toolbarSize = toolBar.preferredSize.width
    val statusBarSize = statusBar.preferredSize.width

    return maxOf(gamePanelSize, toolbarSize, statusBarSize)
  }

  /**
   * Calculate the size of the grid and toolBar to use as the basis for our JFrame height.
   */
  fun calcFrameHeight(): Int {
    val gridSizeToUse = gamePanel.getCalculatedHeight()
    val toolbarSize = toolBar.preferredSize.height
    val statusBarSize = statusBar.preferredSize.height

    return toolbarSize + gridSizeToUse + statusBarSize
  }

}