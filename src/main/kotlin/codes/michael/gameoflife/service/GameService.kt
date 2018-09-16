package codes.michael.gameoflife.service

import codes.michael.gameoflife.simulation.LifeSimulation
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

/**
 * Wrapper of the life simulation class to provide ways to start/stop, update, etc.
 */
class GameService(private val lifeSimulation: LifeSimulation) {
  private var isRunning = false
  private var updateFunctions: MutableList<(v: Array<Array<Boolean>>) -> Unit> = mutableListOf()

  var generationCount = 0

  /**
   * Update an individual value in the simulation.
   */
  fun updateValue(row: Int, col: Int, alive: Boolean) {
    lifeSimulation.updateValue(row, col, alive)
  }

  /**
   * Start running the simulation automatically.
   */
  fun start() {
    if (!isRunning) {
      isRunning = true

      //Launch a Kotlin coroutine to handle running the successive generations
      launch {
        while (isRunning) {
          nextGeneration()
          //TODO: Make this configurable somehow in the UI.
          delay(500)
        }
      }
    }
  }

  /**
   * Stop the running simulation.
   */
  fun stop() {
    isRunning = false
  }

  /**
   * Compute the next generation.
   */
  fun nextGeneration() {
    lifeSimulation.computeNextGeneration()
    generationCount++

    applyUpdateCallbacks()
  }

  /**
   * Reset the simulation
   */
  fun clear() {
    isRunning = false
    lifeSimulation.clear()
    applyUpdateCallbacks()
  }

  /**
   * Register a function to receive a callback when the game state updates.
   */
  fun addOnUpdateFunction(fn: (v: Array<Array<Boolean>>) -> Unit) {
    updateFunctions.add(fn)
  }

  /**
   * Call any of our registered update callback functions.
   */
  private fun applyUpdateCallbacks() {
    updateFunctions.forEach {
      it(lifeSimulation.getValues())
    }
  }
}