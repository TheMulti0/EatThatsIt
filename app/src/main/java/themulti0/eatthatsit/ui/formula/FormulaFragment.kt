package themulti0.eatthatsit.ui.formula

import android.os.Bundle
import android.view.View
import themulti0.eatthatsit.R
import themulti0.eatthatsit.databinding.BenedictFragmentBinding
import themulti0.eatthatsit.services.benedictformula.BenedictFormulaService
import themulti0.eatthatsit.services.benedictformula.bmr.HarrisBenedictFormula
import themulti0.eatthatsit.services.benedictformula.models.Person
import themulti0.eatthatsit.ui.XmlFragment

class FormulaFragment : XmlFragment(R.layout.formula_fragment) {

    override fun onViewCreated(view: View, savedInstanceBundle: Bundle?): Unit {
        super.onViewCreated(view, savedInstanceBundle)

        arguments.
        val vm = FormulaViewModel()
        val binding = BenedictFragmentBinding.bind(view)
        binding.lifecycleOwner = this
        binding.vm = vm

        val person: Person = arguments?.get("person") as? Person ?: return
        val result: Double = BenedictFormulaService(HarrisBenedictFormula()).calculate(person, 1.75)
        vm.result.value = "Result is $result"
    }
}