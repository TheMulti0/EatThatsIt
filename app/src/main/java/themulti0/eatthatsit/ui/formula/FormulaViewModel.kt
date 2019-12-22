package themulti0.eatthatsit.ui.formula

import androidx.lifecycle.LiveData
import themulti0.eatthatsit.ui.benedict.BenedictFormulaType

class FormulaViewModel(
    val type: BenedictFormulaType,
    val result: LiveData<String>
)