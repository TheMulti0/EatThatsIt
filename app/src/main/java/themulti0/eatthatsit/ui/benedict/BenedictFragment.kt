package themulti0.eatthatsit.ui.benedict

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import themulti0.eatthatsit.R
import themulti0.eatthatsit.databinding.FragmentBenedictBinding
import themulti0.eatthatsit.services.benedictFormula.BenedictFormulaService
import themulti0.eatthatsit.services.benedictFormula.bmr.HarrisBenedictFormula
import themulti0.eatthatsit.services.benedictFormula.bmr.IBenedictBmrFormula
import themulti0.eatthatsit.services.benedictFormula.bmr.MiffinStJeorBenedictFormula
import themulti0.eatthatsit.services.benedictFormula.bmr.RozaShizgalBenedictFormula
import themulti0.eatthatsit.services.benedictFormula.models.*
import themulti0.eatthatsit.ui.MultiArrayAppender

class BenedictFragment : Fragment(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private lateinit var benedictViewModel: BenedictViewModel
    private var formulaType: BenedictFormulaType = BenedictFormulaType.Average

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        benedictViewModel = ViewModelProviders.of(this).get(BenedictViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_benedict, container, false)

        var binding: FragmentBenedictBinding = FragmentBenedictBinding.bind(root)
        binding.lifecycleOwner = this
        binding.vm = benedictViewModel

        bindViewToVm(R.id.inputWeight, root, benedictViewModel::inputWeight::set)
        bindViewToVm(R.id.inputHeight, root, benedictViewModel::inputHeight::set)
        bindViewToVm(R.id.inputAge, root, benedictViewModel::inputAgeInYears::set)
        bindViewToVm(R.id.inputPal, root, benedictViewModel::inputPal::set)

        root.findViewById<Button>(R.id.calculateButton).setOnClickListener(this)

        val spinner: Spinner = root.findViewById(R.id.formula_spinner)
        spinner.onItemSelectedListener = this

        MultiArrayAppender(
            root.context,
            android.R.layout.select_dialog_item,
            resources.getStringArray(R.array.short_benedict_formula_arrays),
            resources.getStringArray(R.array.long_benedict_formula_arrays))
        .also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.select_dialog_item)
            spinner.adapter = adapter
        }

        return root
    }

    private fun bindViewToVm(id: Int, root: View, propertySetter: (Double) -> Unit) {
        var inputView: TextView = root.findViewById(id)
        inputView.doAfterTextChanged { text ->
            try {
                propertySetter(text.toString().toDouble())
            } catch (e: NumberFormatException) {
            }
        }
    }

    override fun onClick(v: View?) {
        var calories: Double = when (this.formulaType) {

            BenedictFormulaType.Average -> {
                (calculateCalories(HarrisBenedictFormula()) +
                        calculateCalories(RozaShizgalBenedictFormula()) +
                        calculateCalories(MiffinStJeorBenedictFormula())) / 3
            }

            BenedictFormulaType.HarrisBenedict -> calculateCalories(HarrisBenedictFormula())
            BenedictFormulaType.RozaShizgal -> calculateCalories(RozaShizgalBenedictFormula())
            BenedictFormulaType.MiffinStJeor -> calculateCalories(MiffinStJeorBenedictFormula())

        }
        benedictViewModel.result.value = "Result is $calories"
    }

    private fun calculateCalories(harrisBenedictFormula: IBenedictBmrFormula): Double {
        val person = Person(
            Gender.Male,
            Weight(benedictViewModel.inputWeight, WeightVolume.Kilogram),
            Length(benedictViewModel.inputHeight, LengthVolume.Centimeter),
            benedictViewModel.inputAgeInYears
        )

        return BenedictFormulaService(harrisBenedictFormula).calculate(
            person,
            benedictViewModel.inputPal
        )
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        this.formulaType = BenedictFormulaType.fromInt(position);
    }
}