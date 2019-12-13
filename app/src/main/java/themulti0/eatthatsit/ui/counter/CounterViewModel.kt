package themulti0.eatthatsit.ui.counter

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import themulti0.eatthatsit.ui.extensions.AcceleratedInvoker
import java.util.concurrent.TimeUnit

class CounterViewModel(
    private val database: CounterDatabase,
    val counterName: String
) : BaseObservable() {

    private val invoker: AcceleratedInvoker = AcceleratedInvoker(
        TimeUnit.MILLISECONDS,
        this::incrementDouble,
        250,
        30,
        75
    )

    var counter: String = this.database.counter
        @Bindable
        get
        set(value: String) {
            if (field != value) {
                field = value
            }
            notifyPropertyChanged(BR.counter) // binding field id = 2

            database.counter = value
        }

    fun startIncrementation(incrementation: Double) {
        invoker.start(incrementation)
    }

    fun stopIncrementation() {
        invoker.stop()
    }

    fun incrementDouble(argument: Any?) {
        var value: Double = counter.toDoubleOrNull() ?: return
        val incrementation: Double = argument as? Double ?: return
        value += incrementation

        val stringValue = value.toString()
        counter =
            if (value % 1 == 0.0) stringValue.dropLast(2) // Remove .0
            else stringValue
    }
}