package codes.michael.gameoflife.simulation

import codes.michael.gameoflife.GRID_COLS
import codes.michael.gameoflife.GRID_ROWS

class LifeSimulation {
  private var grid = emptyGrid()

  /**
   * Apply all of the game of life rules
   */
  fun computeNextGeneration() {
    val newGeneration = emptyGrid()

    for (row in 0..(GRID_ROWS - 1)) {
      for (col in 0..(GRID_COLS - 1)) {
        val currentVal = grid[row][col]
        newGeneration[row][col] = determineCellValue(row, col, currentVal)
      }
    }

    grid = newGeneration
  }

  /**
   * Reset the grid
   */
  fun clear() {
    grid = emptyGrid()
  }

  /**
   * Create an empty structure for the next generation
   */
  fun emptyGrid() = Array(GRID_ROWS, {
    Array(GRID_COLS) {
      false
    }
  })

  /**
   * Apply the game rules to determine if the cell should be alive in the next generation
   */
  fun determineCellValue(row: Int, col: Int, isAliveCurrently: Boolean): Boolean {
    val neighbors = countNeighbors(row, col)

    return if (isAliveCurrently) {
      // A live cell with less than two live neighbors dies.
      if (neighbors < 2) {
        false
      }
      // A live cell with two or three live neighbors lives.
      else if (neighbors == 2 || neighbors == 3) {
        true
      }
      // A live cell with more than three live neighbors dies.
      else {
        false
      }
    }
    else {
      // A dead cell with exactly three live cells becomes alive.
      if (neighbors == 3) {
        true
      }
      else {
        false
      }
    }
  }

  /**
   * Compute the number of neighbors that the item has
   */
  fun countNeighbors(row: Int, col: Int): Int {
    val neighbors: MutableList<Boolean> = mutableListOf()

    with(neighbors) {
      //Row above
      add(getNeighborValue(row - 1, col - 1))
      add(getNeighborValue(row - 1, col))
      add(getNeighborValue(row - 1, col + 1))

      //Same Row
      add(getNeighborValue(row, col - 1))
      add(getNeighborValue(row, col + 1))

      //Row below
      add(getNeighborValue(row + 1, col - 1))
      add(getNeighborValue(row + 1, col))
      add(getNeighborValue(row + 1, col + 1))
    }

    return neighbors.count { it == true }
  }

  /**
   * Determine the value of the specified neighbor
   */
  fun getNeighborValue(row: Int, col: Int): Boolean {
    return if (row < 0 || row > (GRID_ROWS - 1) || col < 0 || col > (GRID_COLS - 1)) {
      false
    }
    else {
      grid[row][col]
    }
  }

  /**
   * Update the specified value
   */
  fun updateValue(row: Int, col: Int, alive: Boolean) {
    grid[row][col] = alive
  }

  /**
   * Get a copy of the internal values of the game state. Don't return references to the internal state so it can't
   * be modified.
   */
  fun getValues() : Array<Array<Boolean>> {
    return grid.clone()
  }
}