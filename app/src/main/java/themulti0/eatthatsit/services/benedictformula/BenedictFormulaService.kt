package themulti0.eatthatsit.services.benedictformula

import themulti0.eatthatsit.services.benedictformula.models.Person
import themulti0.eatthatsit.services.benedictformula.bmr.IBenedictBmrFormula

class BenedictFormulaService(private val bmrFormula: IBenedictBmrFormula) {
    private fun determineTotalIntake(pal: Double, bmr: Double): Double = pal * bmr

    fun calculate(person: Person, pal: Double) =
        determineTotalIntake(pal, bmrFormula.calculateBmr(person))
}