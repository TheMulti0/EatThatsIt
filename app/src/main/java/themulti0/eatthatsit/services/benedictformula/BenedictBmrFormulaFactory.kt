package themulti0.eatthatsit.services.benedictformula

import themulti0.eatthatsit.services.benedictformula.bmr.*
import themulti0.eatthatsit.services.benedictformula.models.BenedictFormulaType

object BenedictBmrFormulaFactory {
    fun create(type: BenedictFormulaType): IBenedictBmrFormula {
        return when (type) {
            BenedictFormulaType.Average -> AverageBenedictFormula()
            BenedictFormulaType.HarrisBenedict -> HarrisBenedictFormula()
            BenedictFormulaType.MiffinStJeor -> MiffinStJeorBenedictFormula()
            BenedictFormulaType.RozaShizgal -> RozaShizgalBenedictFormula()
        }
    }
}