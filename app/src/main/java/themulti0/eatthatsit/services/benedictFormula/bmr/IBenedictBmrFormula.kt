package themulti0.eatthatsit.services.benedictFormula.bmr

import themulti0.eatthatsit.services.benedictFormula.models.Person

interface IBenedictBmrFormula {
    fun calculateBmr(person: Person): Double
}