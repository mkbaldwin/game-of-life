package codes.michael.gameoflife

import codes.michael.gameoflife.service.GameService
import codes.michael.gameoflife.simulation.LifeSimulation
import codes.michael.gameoflife.ui.ApplicationFrame
import java.awt.Color


val GRID_ROWS = 40
val GRID_COLS = 40
val CELL_SIZE = 25
val BORDER_WIDTH = 1

val BORDER_COLOR = Color.GRAY
val DEAD_COLOR = Color.LIGHT_GRAY
val ALIVE_COLOR = Color.BLUE

/**
 * Kotlin application starting point.
 */
fun main(args: Array<String>) {
  val lifeSimulation = LifeSimulation()
  val gameService = GameService(lifeSimulation)

  ApplicationFrame(gameService)
}