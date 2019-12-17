package themulti0.eatthatsit.ui.person

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.person_fragment.*
import themulti0.eatthatsit.R
import themulti0.eatthatsit.databinding.PersonFragmentBinding
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

        val db = PersonDatabase(
            context!!.getSharedPreferences(
                "Person",
                Context.MODE_PRIVATE
            )
        )

        val binding = PersonFragmentBinding.bind(view)
        binding.lifecycleOwner = this

        val genderId =
            if (db.gender == Gender.Male)
                male_radio.id
            else
                female_radio.id
        gender_radios.check(genderId)
        input_weight.setText(db.weight.amount.toString())
        input_height.setText(db.height.amount.toString())
        input_age.setText(db.age.toString())

        male_radio.setOnClickListener {
            db.gender = Gender.Male
        }

        female_radio.setOnClickListener {
            db.gender = Gender.Female
        }

        input_weight.bindToViewModel {
            val amount: Double = it.toDoubleOrNull() ?: return@bindToViewModel
            db.weight = Weight(amount, db.weight.volume)
        }
        input_height.bindToViewModel {
            val amount = it.toDoubleOrNull() ?: return@bindToViewModel
            db.height = Length(amount, db.height.volume)
        }
        input_age.bindToViewModel {
            db.age = it.toDoubleOrNull() ?: return@bindToViewModel
        }
    }
}