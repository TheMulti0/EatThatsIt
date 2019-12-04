package themulti0.eatthatsit.services.benedictformula.bmr

import themulti0.eatthatsit.services.benedictformula.models.Person

interface IBenedictBmrFormula {
    fun calculateBmr(person: Person): Double
}