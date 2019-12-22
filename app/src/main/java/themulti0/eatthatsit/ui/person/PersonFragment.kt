package themulti0.eatthatsit.ui.person

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.person_fragment.*
import themulti0.eatthatsit.R
import themulti0.eatthatsit.services.benedictformula.models.Gender
import themulti0.eatthatsit.services.benedictformula.models.Length
import themulti0.eatthatsit.services.benedictformula.models.Weight
import themulti0.eatthatsit.ui.extensions.bindToViewModel

class PersonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.person_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceBundle: Bundle?): Unit {
        super.onViewCreated(view, savedInstanceBundle)

        val vm = PersonViewModel(
            PersonDatabase(
                context!!.getSharedPreferences(
                    "Person",
                    Context.MODE_PRIVATE
                )
            )
        )

        val genderId =
            if (vm.gender == Gender.Male)
                male_radio.id
            else
                female_radio.id
        gender_radios.check(genderId)
        input_weight.setText(vm.weight.amount.toString())
        input_height.setText(vm.height.amount.toString())
        input_age.setText(vm.age.toString())

        male_radio.setOnClickListener {
            vm.gender = Gender.Male
        }

        female_radio.setOnClickListener {
            vm.gender = Gender.Female
        }

        input_weight.bindToViewModel {
            val amount: Double = it.toDoubleOrNull() ?: return@bindToViewModel
            vm.weight = Weight(amount, vm.weight.volume)
        }
        input_height.bindToViewModel {
            val amount = it.toDoubleOrNull() ?: return@bindToViewModel
            vm.height = Length(amount, vm.height.volume)
        }
        input_age.bindToViewModel {
            vm.age = it.toDoubleOrNull() ?: return@bindToViewModel
        }
        calculate_button.setOnClickListener {
            vm.save()
            it.findNavController().navigate(
                R.id.action_calculate_benedict,
                bundleOf("person" to vm.person)
            )
        }

    }
}