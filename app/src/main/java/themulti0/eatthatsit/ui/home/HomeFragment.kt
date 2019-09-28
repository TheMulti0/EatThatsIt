package themulti0.eatthatsit.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import themulti0.eatthatsit.R
import themulti0.eatthatsit.databinding.FragmentHomeBinding
import themulti0.eatthatsit.services.benedictFormula.BenedictFormulaService
import themulti0.eatthatsit.services.benedictFormula.bmr.HarrisBenedictFormula
import themulti0.eatthatsit.services.benedictFormula.models.*

class HomeFragment : Fragment(), View.OnClickListener {
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        var binding: FragmentHomeBinding = FragmentHomeBinding.bind(root)
        binding.lifecycleOwner = this
        binding.vm = homeViewModel

        bindViewToVm(R.id.inputWeight, root, homeViewModel::inputWeight::set)
        bindViewToVm(R.id.inputHeight, root, homeViewModel::inputHeight::set)
        bindViewToVm(R.id.inputAge, root, homeViewModel::inputAgeInYears::set)

        root.findViewById<Button>(R.id.calculateButton).setOnClickListener(this)

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
        var a = BenedictFormulaService(HarrisBenedictFormula()).calculate(Person(Gender.Male, Weight(homeViewModel.inputWeight, WeightVolume.Kilogram), Length(homeViewModel.inputHeight, LengthVolume.Centimeter), homeViewModel.inputAgeInYears), 1.75)
        homeViewModel.result.value = "Result is $a"
    }
}