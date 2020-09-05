package themulti0.eatthatsit.ui.person

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import themulti0.eatthatsit.services.benedictformula.models.*
import themulti0.eatthatsit.ui.extensions.format
import java.text.DecimalFormat
import java.text.NumberFormat

class PersonViewModel(private val database: PersonDatabase) : BaseObservable() {

    private val decimalPoints = 1

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

    var weightAmount: String = format(this.database.weight.amount, decimalPoints)
        @Bindable
        get
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.weightAmount)
            }
        }
    private val weight: Weight
        get() = Weight(format(weightAmount.toDouble(), decimalPoints).toDouble(), WeightVolume.Kilogram)

    var heightLength: String = format(this.database.height.amount, decimalPoints)
        @Bindable
        get
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.heightLength)
            }
        }
    private val height: Length
        get() = Length(format(heightLength.toDouble(), decimalPoints).toDouble(), LengthVolume.Centimeter)

    var age: String = format(this.database.age, decimalPoints)
        @Bindable
        get
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.age)
            }
        }

    val person: Person
        get() = Person(
            gender,
            weight,
            height,
            format(age.toDouble(), decimalPoints).toDouble()
        )

    fun save() {
        database.gender = gender
        database.weight = weight
        database.height = height
        database.age = age.toDouble()
    }
}