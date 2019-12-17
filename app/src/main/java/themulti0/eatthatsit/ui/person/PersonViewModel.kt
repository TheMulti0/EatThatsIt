package themulti0.eatthatsit.ui.person

import android.content.SharedPreferences
import themulti0.eatthatsit.services.benedictformula.models.*

class PersonDatabase(private val preferences: SharedPreferences) {
    private val defaultDouble: Double = 0.0
    private val defaultFloat: Float = 0.0F
    private val defaultInt: Int = 0

    private val genderKey = "gender"
    private val weightAmountKey = "weight"
    private val weightVolumeKey = "isKg"
    private val lengthAmountKey = "height"
    private val lengthVolumeKey = "isMetric"
    private val ageKey = "age"

    private val emptyPerson: Person =
        Person(
            Gender.Male,
            Weight(defaultDouble, WeightVolume.Kilogram),
            Length(defaultDouble, LengthVolume.Centimeter),
            defaultDouble
        )

    var person: Person = emptyPerson
        get() =
            Person(gender, weight, height, age)
        set(value: Person) {
            field = value
            if (field.gender != value.gender) {
                gender = value.gender
            }
            if (field.weight != value.weight) {
                weight = value.weight
            }
            if (field.height != value.height) {
                height = value.height
            }
            if (field.age != value.age) {
                age = value.age
            }
        }

    var gender: Gender
        get() = Gender.fromInt(preferences.getInt(genderKey, defaultInt))
        set(value: Gender) {
            preferences.edit().putInt(genderKey, value.value).apply()
        }

    var weight: Weight
        get() {
            return Weight(
                preferences.getFloat(weightAmountKey, defaultFloat).toDouble(),
                if (preferences.getBoolean(weightVolumeKey, true))
                    WeightVolume.Kilogram
                else WeightVolume.Pound
            )
        }
        set(value: Weight) {
            preferences
                .edit()
                .putFloat(weightAmountKey, value.amount.toFloat())
                .putBoolean(weightVolumeKey, value.volume == WeightVolume.Kilogram)
                .apply()
        }

    var height: Length
        get() {
            return Length(
                preferences.getFloat(lengthAmountKey, defaultFloat).toDouble(),
                if (preferences.getBoolean(lengthVolumeKey, true))
                    LengthVolume.Centimeter
                else
                    LengthVolume.Inch
            )
        }
        set(value: Length) {
            preferences
                .edit()
                .putFloat(lengthAmountKey, value.amount.toFloat())
                .putBoolean(lengthVolumeKey, value.volume == LengthVolume.Centimeter)
                .apply()
        }

    var age: Double
        get() = preferences.getFloat(ageKey, defaultFloat).toDouble()
        set(value: Double) {
            preferences
                .edit()
                .putFloat(ageKey, value.toFloat())
                .apply()
        }
}