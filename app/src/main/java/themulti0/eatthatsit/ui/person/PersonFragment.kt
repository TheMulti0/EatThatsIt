package themulti0.eatthatsit.ui.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.person_fragment.*
import themulti0.eatthatsit.R
import themulti0.eatthatsit.databinding.PersonFragmentBinding
import themulti0.eatthatsit.services.benedictformula.models.Gender
import themulti0.eatthatsit.ui.extensions.bindToViewModel

class PersonFragment : Fragment() {

    lateinit var vm: PersonViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.person_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceBundle: Bundle?): Unit {
        super.onViewCreated(view, savedInstanceBundle)

        vm = PersonViewModel()

        val binding = PersonFragmentBinding.bind(view)
        binding.lifecycleOwner = this
        binding.vm = vm

        male_radio.setOnClickListener {
            vm.person.gender = Gender.Male
        }

        female_radio.setOnClickListener {
            vm.person.gender = Gender.Female
        }

        input_weight.bindToViewModel {
            vm.person.weight.amount = it.toDoubleOrNull() ?: return@bindToViewModel
        }
        input_height.bindToViewModel {
            vm.person.height.amount = it.toDoubleOrNull() ?: return@bindToViewModel
        }
        input_age.bindToViewModel {
            vm.person.ageInYears = it.toDoubleOrNull() ?: return@bindToViewModel
        }
    }
}