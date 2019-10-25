package themulti0.eatthatsit.ui.benedict

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_benedict.*
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_benedict, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceBundle: Bundle?): Unit {
        super.onViewCreated(view, savedInstanceBundle)

        benedictViewModel = ViewModelProviders.of(this).get(BenedictViewModel::class.java)

        val binding: FragmentBenedictBinding = FragmentBenedictBinding.bind(view)
        binding.lifecycleOwner = this
        binding.vm = benedictViewModel // XML side binding for viewResult

        inputWeight?.bindToViewModel(benedictViewModel::inputWeight::set)
        inputHeight?.bindToViewModel(benedictViewModel::inputHeight::set)
        inputAge?.bindToViewModel(benedictViewModel::inputAgeInYears::set)
        inputPal?.bindToViewModel(benedictViewModel::inputPal::set)

        calculateButton?.setOnClickListener(this)

        val context = view.context
        gender_spinner?.bindToAdapter(
            this,
            ArrayAdapter.createFromResource(
                context,
                R.array.benedict_gender_array,
                android.R.layout.select_dialog_item
            )
        )

        formula_spinner?.bindToAdapter(
            this,
            MultiArrayAppender(
                context,
                resources.getStringArray(R.array.short_benedict_formula_array),
                resources.getStringArray(R.array.long_benedict_formula_array),
                android.R.layout.select_dialog_item
            )
        )
    }

    private fun <T : Any> Spinner.bindToAdapter(
        itemSelectedListener: AdapterView.OnItemSelectedListener,
        arrayAdapter: ArrayAdapter<T>
    ) {
        this.onItemSelectedListener = itemSelectedListener

        arrayAdapter.also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.select_dialog_item)
            this.adapter = adapter
        }
    }

    private fun EditText.bindToViewModel(propertySetter: (Double) -> Unit): Unit {
        this.doAfterTextChanged { text ->
            try {
                propertySetter(text.toString().toDouble())
            } catch (e: NumberFormatException) {
            }
        }
    }

    override fun onClick(v: View?) {
        val calories: Double = when (this.formulaType) {

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
        when (parent) {
            gender_spinner -> this.benedictViewModel.inputGender = Gender.fromInt(position)
            formula_spinner -> this.formulaType = BenedictFormulaType.fromInt(position)
        }
    }
}