package codes.michael.gameoflife.service

import codes.michael.gameoflife.simulation.GameGrid

class GameService(private val gameGrid: GameGrid) {

  fun updateValue(row: Int, col: Int, alive: Boolean) {
    gameGrid.updateValue(row, col, alive)
  }
}