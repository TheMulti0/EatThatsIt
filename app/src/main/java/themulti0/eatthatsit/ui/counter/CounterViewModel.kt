package themulti0.eatthatsit.ui.counter

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import themulti0.eatthatsit.database.NutritionDao
import themulti0.eatthatsit.database.NutritionEntity
import themulti0.eatthatsit.ui.extensions.AcceleratedInvoker
import java.util.concurrent.TimeUnit

class CounterViewModel(private val nutritionDao: NutritionDao) : BaseObservable() {

    private val invoker: AcceleratedInvoker =
        AcceleratedInvoker(
            TimeUnit.MILLISECONDS,
            this::incrementDouble,
            250,
            30,
            75
        )

    private lateinit var nutritionEntity: NutritionEntity

    var counterName: String? = null

    var counter: String = "0"
        @Bindable
        get
        set(value) {
            if (field != value) {
                field = value
            }
            notifyPropertyChanged(BR.counter) // binding field id = 2

            updateDatabase()
        }

    private fun updateDatabase() {
        try {
            val result: NutritionEntity? = nutritionDao.findById(nutritionEntity.id)
            if (result != null) {
                result.value = counter.toDoubleOrNull()
                nutritionEntity = result
                nutritionDao.update(result)
            }
        } catch (e: Exception) {
            val idLessEntity = NutritionEntity(0, counterName, counter.toDoubleOrNull())
            val id: Long = nutritionDao.insert(idLessEntity)
            nutritionEntity = NutritionEntity(id, counterName, counter.toDoubleOrNull())
        }
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