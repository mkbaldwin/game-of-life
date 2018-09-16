package codes.michael.gameoflife.ui

import java.awt.BorderLayout
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.border.EmptyBorder

class StatusBar: JPanel() {
  private val generationCountLabel = JLabel()

  init {
    layout = BorderLayout()
    isVisible = true
    isOpaque = true
    border = EmptyBorder(5, 15, 5,0)

    //Configure the label
    with(generationCountLabel) {
      isVisible = true
      isOpaque = true

    }
    add(generationCountLabel, BorderLayout.WEST)

    //Set a default value
    updateGenerationCount(0)
  }

  fun updateGenerationCount(generation: Int) {
    generationCountLabel.text = "Generation: $generation"
  }
}