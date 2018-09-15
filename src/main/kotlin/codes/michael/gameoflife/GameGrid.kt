package codes.michael.gameoflife

class GameGrid {
  val grid = Array(GRID_ROWS, {
    Array<Cell>(GRID_COLS)  {
      Cell()
    }
  })


}