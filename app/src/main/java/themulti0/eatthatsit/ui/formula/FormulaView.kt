package themulti0.eatthatsit.ui.formula

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import themulti0.eatthatsit.R
import themulti0.eatthatsit.databinding.FormulaViewBinding
import themulti0.eatthatsit.services.benedictformula.BenedictBmrFormulaFactory
import themulti0.eatthatsit.services.benedictformula.BenedictFormulaService

class FormulaView(context: Context) : LinearLayout(context) {

    val vm = FormulaViewModel()

    init {
        val from = LayoutInflater.from(context)!!
        val inflate = from.inflate(R.layout.formula_view, null)
        addView(inflate)

        var binding = FormulaViewBinding.bind(inflate)
        binding.vm = vm
    }

    fun calculate() {
        val info: FormulaInformation = vm.info.value ?: return

        val service = BenedictFormulaService(
            BenedictBmrFormulaFactory
                .create(info.type)
        )

        vm.result.value = service.calculate(info.person, info.pal)
    }
}