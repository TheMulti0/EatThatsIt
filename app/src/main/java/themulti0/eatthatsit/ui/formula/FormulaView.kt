package themulti0.eatthatsit.ui.formula

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.formula_view.view.*
import themulti0.eatthatsit.R
import themulti0.eatthatsit.services.benedictformula.BenedictBmrFormulaFactory
import themulti0.eatthatsit.services.benedictformula.BenedictFormulaService

class FormulaView(context: Context) : LinearLayout(context) {

    init {
        val from = LayoutInflater.from(context)!!
        val inflate = from.inflate(R.layout.formula_view, null)
        addView(inflate)
    }

    fun calculate(info: FormulaInformation) {

        val service = BenedictFormulaService(
            BenedictBmrFormulaFactory
                .create(info.type)
        )

        if (text_benedict.text.isNullOrEmpty()) {
            text_benedict.text = info.type.formulaName
        }
        text_result.text = service.calculate(info.person, info.pal).toString()
    }
}