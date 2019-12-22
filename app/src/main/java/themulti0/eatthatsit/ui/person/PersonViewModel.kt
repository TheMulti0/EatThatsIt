package themulti0.eatthatsit.ui.person

import themulti0.eatthatsit.services.benedictformula.models.Gender
import themulti0.eatthatsit.services.benedictformula.models.Length
import themulti0.eatthatsit.services.benedictformula.models.Person
import themulti0.eatthatsit.services.benedictformula.models.Weight

class PersonViewModel(val database: PersonDatabase) {

    val person: Person
        get() = Person(
            gender,
            weight,
            height,
            age
        )

    var gender: Gender = database.gender
    var weight: Weight = database.weight
    var height: Length = database.height
    var age: Double = database.age

    fun save(): Unit {
        database.gender = gender
        database.weight = weight
        database.height = height
        database.age = age
    }
}