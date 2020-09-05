package themulti0.eatthatsit.ui.person

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import themulti0.eatthatsit.services.benedictformula.models.*
import java.text.DecimalFormat
import java.text.NumberFormat

fun NumberFormat.formatDouble(value: Double): Double = this.format(value).toDouble()

class PersonViewModel(private val database: PersonDatabase) : BaseObservable() {

    private val format: DecimalFormat = DecimalFormat("##.#")

    var male: Boolean = this.database.gender == Gender.Male
        @Bindable
        get
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.male)
            }
        }
    val female: Boolean = this.database.gender == Gender.Female
    private val gender: Gender
        get() = if (male) Gender.Male else Gender.Female

    var weightAmount: Double = format.formatDouble(this.database.weight.amount)
        @Bindable
        get
        set(value) {
            if (field != value) {
                field = format.formatDouble(value)
                notifyPropertyChanged(BR.weightAmount)
            }
        }
    private val weight: Weight
        get() = Weight(weightAmount, WeightVolume.Kilogram)

    var heightLength: Double = format.formatDouble(this.database.height.amount)
        @Bindable
        get
        set(value) {
            if (field != value) {
                field = format.formatDouble(value)
                notifyPropertyChanged(BR.heightLength)
            }
        }
    private val height: Length
        get() = Length(heightLength, LengthVolume.Centimeter)

    var age: Double = format.formatDouble(this.database.age)
        @Bindable
        get
        set(value) {
            if (field != value) {
                field = format.formatDouble(value)
                notifyPropertyChanged(BR.age)
            }
        }

    val person: Person
        get() = Person(
            gender,
            weight,
            height,
            age
        )

    fun save(): Unit {
        database.gender = gender
        database.weight = weight
        database.height = height
        database.age = age
    }
}