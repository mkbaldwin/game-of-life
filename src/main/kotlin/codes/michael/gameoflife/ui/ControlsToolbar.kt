package codes.michael.gameoflife.ui

import codes.michael.gameoflife.service.GameService
import java.util.*
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JSlider
import javax.swing.JToolBar

class ControlsToolbar(val gameService: GameService) : JToolBar() {
  private val minDelay = 25
  private val maxDelay = 1000
  private val delayStep = 25

  init {
    isFloatable = false
    isRollover = true

    createNextButton()
    createClearButton()
    createStartButton()
    createStopButton()
    createDelaySlider()
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

  /**
   * Create a slider to control delay.
   */
  fun createDelaySlider() {
    val label = JLabel("Generation Delay ${gameService.delayMs}ms")
    label.size.width = 250

    add(label)

    val labels = Hashtable<Int, JLabel>()
    for (i in minDelay until (maxDelay + 1) step (delayStep * 2)) {
      labels[i] = JLabel(i.toString())
    }

    val slider = JSlider(JSlider.HORIZONTAL, minDelay, maxDelay, gameService.delayMs)
    with(slider) {
      majorTickSpacing = delayStep
      paintTicks = true
      snapToTicks = true
      labelTable = labels
      paintLabels = true

      addChangeListener({
        val source = it.getSource() as JSlider
        if (!source.getValueIsAdjusting()) {
          gameService.delayMs = source.value
          label.text = "Generation Delay ${gameService.delayMs}ms"
        }
      })
    }

    add(slider)
  }
}