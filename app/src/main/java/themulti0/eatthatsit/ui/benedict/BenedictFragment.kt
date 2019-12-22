package themulti0.eatthatsit.ui.benedict

import android.os.Bundle
import android.view.View
import themulti0.eatthatsit.R
import themulti0.eatthatsit.databinding.BenedictFragmentBinding
import themulti0.eatthatsit.services.benedictformula.BenedictFormulaService
import themulti0.eatthatsit.services.benedictformula.bmr.HarrisBenedictFormula
import themulti0.eatthatsit.services.benedictformula.models.Person
import themulti0.eatthatsit.ui.XmlFragment

class BenedictFragment : XmlFragment(R.layout.benedict_fragment) {

    override fun onViewCreated(view: View, savedInstanceBundle: Bundle?): Unit {
        super.onViewCreated(view, savedInstanceBundle)

        val vm = BenedictViewModel()
        val binding = BenedictFragmentBinding.bind(view)
        binding.lifecycleOwner = this
        binding.vm = vm

        val person: Person = arguments?.get("person") as? Person ?: return
        val result: Double = BenedictFormulaService(HarrisBenedictFormula()).calculate(person, 1.75)
        vm.result.value = "Result is $result"
    }
}