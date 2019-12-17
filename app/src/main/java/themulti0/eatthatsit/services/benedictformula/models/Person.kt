package themulti0.eatthatsit.services.benedictformula.models

import java.io.Serializable

data class Person(
    var gender: Gender,
    val weight: Weight,
    var height: Length,
    var age: Double
) : Serializable {
    companion object {
        val EMPTY: Person =
            Person(
                Gender.Male,
                Weight(0.0, WeightVolume.Kilogram),
                Length(0.0, LengthVolume.Centimeter),
                0.0
            )
    }
}