package codes.michael.gameoflife.ui

import codes.michael.gameoflife.service.GameService
import javax.swing.JButton
import javax.swing.JToolBar

class ControlsToolbar(val gameService: GameService) : JToolBar() {
  init {
    isFloatable = false
    isRollover = true

    createNextButton()
    createClearButton()
    createStartButton()
    createStopButton()
  }

  /**
   * Create the next button & its listener
   */
  fun createNextButton() {
    val btn = JButton("Next")

    btn.addActionListener({
      gameService.nextGeneration()
    })

    add(btn)
  }

  /**
   * Create the clear button & its listener
   */
  fun createClearButton() {
    val btn = JButton("Clear")

    btn.addActionListener({
      gameService.clear()
    })

    add(btn)
  }

  /**
   * Create the start button and its listener
   */
  fun createStartButton() {
    val btn = JButton("Start")

    btn.addActionListener({
      gameService.start()
    })

    add(btn)
  }

  /**
   * Create the start button and its listener
   */
  fun createStopButton() {
    val btn = JButton("Stop")

    btn.addActionListener({
      gameService.stop()
    })

    add(btn)
  }
}