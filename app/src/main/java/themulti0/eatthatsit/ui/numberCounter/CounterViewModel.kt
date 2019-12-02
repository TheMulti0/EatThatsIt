package themulti0.eatthatsit.ui.numberCounter

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class CounterViewModel : BaseObservable() {

    private val defaultTime: Long = 250L
    private val incrementationIntervalMilli: Int = 40
    private val minIncrementationIntervalMilli: Int = 75

    private lateinit var scheduler: ScheduledExecutorService
    private var time: Long = defaultTime

    val buttonIncrementation: Double = 1.0
    var counterName: String? = null

    @get:Bindable
    var counter: String = "0"
        set(value) {
            if (field != value) {
                field = value
            }
            notifyPropertyChanged(BR.counter)
        }



    fun beginIncrementation(incrementation: Double) {
        scheduler = Executors.newSingleThreadScheduledExecutor()
        time = defaultTime

        scheduleIncrementation(incrementation)
    }

    fun endIncrementation() {
        scheduler.shutdownNow()
    }

    fun poop(incrementation: Double) {
        incrementDouble(incrementation)

        if (time > minIncrementationIntervalMilli) {
            time -= incrementationIntervalMilli
        }

        scheduleIncrementation(incrementation)
    }

    private fun scheduleIncrementation(incrementation: Double) {
        scheduler.schedule({ poop(incrementation) }, time, TimeUnit.MILLISECONDS)
    }

    private fun incrementDouble(incrementation: Double) {
        var value: Double? = counter.toDoubleOrNull()
        if (value != null) {
            value += incrementation

            val stringValue = value.toString()
            counter =
                if (value % 1 == 0.0) stringValue.dropLast(2) // Remove .0
                else stringValue
        }
    }
}