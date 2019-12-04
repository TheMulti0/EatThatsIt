package themulti0.eatthatsit.services.benedictformula.bmr

import themulti0.eatthatsit.services.benedictformula.models.Gender
import themulti0.eatthatsit.services.benedictformula.models.Person

class MiffinStJeorBenedictFormula :
    IBenedictBmrFormula {
    override fun calculateBmr(person: Person): Double {
        return when {
            person.gender == Gender.Male -> calculateHumanBmr(person) + 5
            else -> calculateHumanBmr(person) - 161
        }
    }

    private fun calculateHumanBmr(person: Person) =
        calculateBmr(
            person,
            10.0,
            6.25,
            5.0,
            this::metricWeightAccessor,
            this::metricHeightAccessor
        )
}