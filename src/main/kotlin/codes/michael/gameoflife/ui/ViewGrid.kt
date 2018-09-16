package codes.michael.gameoflife.ui

import codes.michael.gameoflife.GRID_COLS
import codes.michael.gameoflife.GRID_ROWS
import codes.michael.gameoflife.service.GameService

class ViewGrid(gameService: GameService) {
  val grid = Array(GRID_ROWS, {r->
    Array(GRID_COLS)  {c ->
      ViewCell(r, c, gameService)
    }
  })

  fun update(values: Array<Array<Boolean>>) {
    for (row in 0..(GRID_ROWS - 1)) {
      for (col in 0..(GRID_COLS - 1)) {
        grid[row][col].alive = values[row][col]
      }
    }
  }
}