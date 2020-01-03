package themulti0.eatthatsit.ui.formula

import themulti0.eatthatsit.services.benedictformula.models.BenedictFormulaType
import themulti0.eatthatsit.services.benedictformula.models.Person

class FormulaInformation(
    val type: BenedictFormulaType,
    val person: Person,
    val pal: Double
)