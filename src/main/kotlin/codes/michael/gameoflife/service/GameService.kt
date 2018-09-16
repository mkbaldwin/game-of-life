package codes.michael.gameoflife.service

import codes.michael.gameoflife.simulation.LifeSimulation
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

/**
 * Wrapper of the life simulation class to provide ways to start/stop, update, etc.
 */
class GameService(private val lifeSimulation: LifeSimulation) {
  private var isRunning = false
  private var generationCount = 0

  private var gameStateListeners: MutableList<(v: Array<Array<Boolean>>) -> Unit> = mutableListOf()
  private var generationCountListeners: MutableList<(v: Int) -> Unit> = mutableListOf()

  var delayMs = 250

  /**
   * Update an individual value in the simulation.
   */
  fun updateValue(row: Int, col: Int, alive: Boolean) {
    lifeSimulation.updateValue(row, col, alive)
    applyUpdateCallbacks()
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
          delay(delayMs)
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
    generationCount = 0
    applyUpdateCallbacks()
  }

  /**
   * Register a function to receive a callback when the game state updates.
   */
  fun addGameStateListener(fn: (v: Array<Array<Boolean>>) -> Unit) {
    gameStateListeners.add(fn)
  }

  /**
   * Register a listener for updates to the generation count.
   */
  fun addGenerationCountListener(fn: (v: Int) -> Unit) {
    generationCountListeners.add(fn)
  }

  /**
   * Load a pattern from a file on the classpath
   */
  fun loadPattern(file: String) {
    clear()

    val inStream = javaClass.getResourceAsStream("/patterns/${file}")
    val stringContent = inStream.readBytes().toString(Charsets.UTF_8)

    //Convert to lines
    val lines = stringContent.split("\n")

    lines.forEach {line ->
      val point = line.split(",")
      if(point[0].length > 0 && point[1].length > 0) {
        lifeSimulation.updateValue(point[0].toInt(), point[1].toInt(), true)
      }
    }

    applyUpdateCallbacks()
  }

  /**
   * Call any of our registered update callback functions.
   */
  private fun applyUpdateCallbacks() {
    gameStateListeners.forEach {
      it(lifeSimulation.getValues())
    }

    generationCountListeners.forEach {
      it(generationCount)
    }
  }
}