package themulti0.eatthatsit.services.benedictFormula.bmr

import themulti0.eatthatsit.services.benedictFormula.models.Gender
import themulti0.eatthatsit.services.benedictFormula.models.Person

class RozaShizgalBenedictFormula : IBenedictBmrFormula {
    override fun calculateBmr(person: Person): Double {
        return when {
            person.gender == Gender.Male -> calculateMaleBmr(person)
            else -> calculateFemaleBmr(person)
        }
    }

    private fun calculateMaleBmr(person: Person) = 88.362 +
            calculateBmr(
                person,
                13.397,
                4.799,
                5.677,
                this::metricWeightAccessor,
                this::metricHeightAccessor
            )

    private fun calculateFemaleBmr(person: Person) = 447.593 +
            calculateBmr(
                person,
                9.247,
                3.098,
                4.330,
                this::metricWeightAccessor,
                this::metricHeightAccessor
            )
}