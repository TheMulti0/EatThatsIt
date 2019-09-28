package themulti0.eatthatsit.services.benedictFormula

import themulti0.eatthatsit.services.benedictFormula.models.Person
import themulti0.eatthatsit.services.benedictFormula.bmr.IBenedictBmrFormula

class BenedictFormulaService(private val bmrFormula: IBenedictBmrFormula) {
    private fun determineTotalIntake(pal: Double, bmr: Double): Double = pal * bmr

    fun calculate(person: Person, pal: Double) =
        determineTotalIntake(pal, bmrFormula.calculateBmr(person))
}