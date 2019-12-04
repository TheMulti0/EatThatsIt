package themulti0.eatthatsit.ui.numberCounter

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class AcceleratedInvoker(
    private val timeUnit: TimeUnit,
    private val callback: (Any?) -> Unit,
    private val startInterval: Long,
    private val intervalSpeed: Long,
    private val minInterval: Long) {

    private var currentInterval: Long = startInterval
    private var scheduler: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()

    fun start(callbackArgument: Any? = null) {
        scheduler = Executors.newSingleThreadScheduledExecutor()
        currentInterval = startInterval

        scheduleTask(callbackArgument)
    }

    fun stop() {
        scheduler.shutdownNow()
    }

    private fun scheduleTask(callbackArgument: Any?) {
        scheduler.schedule(
            { scheduledCallback(callbackArgument) },
            currentInterval,
            timeUnit)
    }

    private fun scheduledCallback(callbackArgument: Any?) {
        callback(callbackArgument)

        if (currentInterval > minInterval) {
            currentInterval -= intervalSpeed
        }

        scheduleTask(callbackArgument)
    }
}