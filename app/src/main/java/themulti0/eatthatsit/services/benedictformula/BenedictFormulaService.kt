package themulti0.eatthatsit.services.benedictformula

import themulti0.eatthatsit.services.benedictformula.bmr.IBenedictBmrFormula
import themulti0.eatthatsit.services.benedictformula.models.Person

class BenedictFormulaService(private val bmrFormula: IBenedictBmrFormula) {
    private fun determineTotalIntake(pal: Double, bmr: Double): Double = pal * bmr

    fun calculate(person: Person, pal: Double): Double =
        determineTotalIntake(pal, bmrFormula.calculateBmr(person))
}