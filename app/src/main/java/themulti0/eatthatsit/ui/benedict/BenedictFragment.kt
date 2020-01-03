package themulti0.eatthatsit.ui.benedict

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.benedict_fragment.*
import themulti0.eatthatsit.R
import themulti0.eatthatsit.databinding.BenedictFragmentBinding
import themulti0.eatthatsit.services.benedictformula.models.BenedictFormulaType
import themulti0.eatthatsit.services.benedictformula.models.Person
import themulti0.eatthatsit.ui.XmlFragment
import themulti0.eatthatsit.ui.extensions.RecyclerViewAdapter
import themulti0.eatthatsit.ui.formula.FormulaInformation
import themulti0.eatthatsit.ui.formula.FormulaView

class BenedictFragment : XmlFragment(R.layout.benedict_fragment) {

    private val formulaTypes: List<BenedictFormulaType> = listOf(
        BenedictFormulaType.Average,
        BenedictFormulaType.HarrisBenedict,
        BenedictFormulaType.MiffinStJeor,
        BenedictFormulaType.RozaShizgal
    )

    override fun onViewCreated(view: View, savedInstanceBundle: Bundle?): Unit {
        super.onViewCreated(view, savedInstanceBundle)

        val vm = BenedictViewModel()
        val binding = BenedictFragmentBinding.bind(view)
        binding.lifecycleOwner = this
        binding.vm = vm

        val person: Person = arguments?.get("person") as? Person ?: return

        val infos = formulaTypes.map { type -> FormulaInformation(type, person, 1.7) }

        formulas_view?.apply {
            setHasFixedSize(true)

            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager

            adapter = RecyclerViewAdapter(
                infos,
                { parent -> FormulaView(parent.context) },
                { view, info ->
                    view.vm.info.value = info
                    view.calculate()
                })

            val dividerItemDecoration =
                DividerItemDecoration(context, linearLayoutManager.orientation)
            addItemDecoration(dividerItemDecoration)
        }
    }
}

