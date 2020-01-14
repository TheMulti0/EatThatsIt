package themulti0.eatthatsit.ui.benedict

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.core.widget.doAfterTextChanged
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

    private val formulaTypes: MutableList<BenedictFormulaType> = mutableListOf(
        BenedictFormulaType.Average,
        BenedictFormulaType.HarrisBenedict,
        BenedictFormulaType.MiffinStJeor,
        BenedictFormulaType.RozaShizgal
    )

    private val formulaInfos: MutableList<FormulaInformation>
        get() =
            formulaTypes.map { type -> FormulaInformation(type, person, vm.pal) }.toMutableList()

    private val vm = BenedictViewModel()
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter<FormulaView, FormulaInformation>
    private lateinit var person: Person

    override fun onViewCreated(view: View, savedInstanceBundle: Bundle?): Unit {
        super.onViewCreated(view, savedInstanceBundle)

        bind(view)

        person = arguments?.get("person") as? Person ?: return

        createFormulas()

        input_pal.doAfterTextChanged(this::palChanged)
    }

    private fun bind(view: View) {
        val binding = BenedictFragmentBinding.bind(view)
        binding.lifecycleOwner = this
        binding.vm = vm
    }

    private fun createFormulas() {
        formulas_view.setHasFixedSize(true)

        val linearLayoutManager = LinearLayoutManager(context)
        formulas_view.layoutManager = linearLayoutManager

        recyclerViewAdapter = RecyclerViewAdapter(
            formulaInfos,
            { parent -> FormulaView(parent.context) },
            { formulaView, info ->
                formulaView.vm.info.value = info
                formulaView.calculate()
            })
        formulas_view.adapter = recyclerViewAdapter

        formulas_view.addItemDecoration(
            DividerItemDecoration(
                context,
                linearLayoutManager.orientation
            )
        )
    }

    private fun palChanged(text: Editable?) {
        vm.pal = text?.toString()?.toDoubleOrNull() ?: return

        recyclerViewAdapter.data = formulaInfos
    }
}

