package themulti0.eatthatsit.services.benedictformula.bmr

import themulti0.eatthatsit.services.benedictformula.models.Person

class AverageBenedictFormula : IBenedictBmrFormula {

    private val formulas: List<IBenedictBmrFormula> = listOf(
        HarrisBenedictFormula(),
        MiffinStJeorBenedictFormula(),
        RozaShizgalBenedictFormula()
    )

    override fun calculateBmr(person: Person): Double {
        var summary = 0.0

        for (formula in formulas) {
            summary += formula.calculateBmr(person)
        }

        return summary / formulas.size
    }
}