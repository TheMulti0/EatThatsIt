package themulti0.eatthatsit.ui.person

import android.content.Context
import android.os.Bundle
import android.util.Xml
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.person_fragment.*
import themulti0.eatthatsit.R
import themulti0.eatthatsit.databinding.CounterFragmentBinding
import themulti0.eatthatsit.databinding.PersonFragmentBinding
import themulti0.eatthatsit.services.benedictformula.models.Gender
import themulti0.eatthatsit.services.benedictformula.models.Length
import themulti0.eatthatsit.services.benedictformula.models.Weight
import themulti0.eatthatsit.ui.XmlFragment
import themulti0.eatthatsit.ui.extensions.bindToViewModel

class PersonFragment : XmlFragment(R.layout.person_fragment) {

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

        val binding = PersonFragmentBinding.bind(view)
        binding.lifecycleOwner = this
        binding.vm = vm

        calculate_button.setOnClickListener {
            vm.save()
            it.findNavController().navigate(
                R.id.action_calculate_benedict,
                bundleOf("person" to vm.person)
            )
        }

    }
}