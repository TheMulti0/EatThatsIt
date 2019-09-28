package themulti0.eatthatsit.services.benedictFormula.bmr

import themulti0.eatthatsit.services.benedictFormula.models.Gender
import themulti0.eatthatsit.services.benedictFormula.models.Person
import themulti0.eatthatsit.services.benedictFormula.models.WeightVolume

class HarrisBenedictFormula :
    IBenedictBmrFormula {
    override fun calculateBmr(person: Person): Double {
        return when {
            person.gender == Gender.Male -> calculateMaleBmr(person)
            else -> calculateFemaleBmr(person)
        }
    }

    private fun calculateMaleBmr(person: Person): Double {
        return when {
            person.weight.volume == WeightVolume.Kilogram -> 66.5 + calculateBmr(
                person, 13.75, 5.003, 6.755
            )
            else -> 66 + calculateBmr(
                person, 6.2, 12.7, 6.76
            )
        }
    }

    private fun calculateFemaleBmr(person: Person): Double {
        return when {
            person.weight.volume == WeightVolume.Pound -> 655.1 + calculateBmr(
                person, 9.563, 1.85, 4.676
            )
            else -> 655.1 + calculateBmr(
                person, 4.35, 4.7, 4.7
            )
        }
    }
}