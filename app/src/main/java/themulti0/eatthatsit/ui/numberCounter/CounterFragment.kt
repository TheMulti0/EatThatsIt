package themulti0.eatthatsit.ui.numberCounter

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.counter_fragment.*
import themulti0.eatthatsit.R
import themulti0.eatthatsit.databinding.CounterFragmentBinding


class CounterFragment : Fragment() {

    private val counterViewModel: CounterViewModel =
        CounterViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.counter_fragment,
            container,
            false
        )
    }

    override fun onInflate(
        context: Context,
        attrs: AttributeSet,
        savedInstanceState: Bundle?
    ) {
        super.onInflate(context, attrs, savedInstanceState)

        try {
            readAttribute(context, attrs)
        }
        catch (e: Exception) { }
    }

    private fun readAttribute(context: Context, attrs: AttributeSet) {
        val obtainedAttributes =
            context.obtainStyledAttributes(attrs, R.styleable.CounterFragment)

        counterViewModel.counterName = obtainedAttributes
            .getText(R.styleable.CounterFragment_counter_name)
            .toString()

        obtainedAttributes.recycle()
    }

    override fun onViewCreated(view: View, savedInstanceBundle: Bundle?): Unit {
        super.onViewCreated(view, savedInstanceBundle)

        val binding = CounterFragmentBinding.bind(view)
        binding.lifecycleOwner = this
        binding.vm = counterViewModel

        minus_button.setOnTouchListener { _, event ->
            si(event, -counterViewModel.buttonIncrementation)
        }
        plus_button.setOnTouchListener { _, event ->
            si(event, counterViewModel.buttonIncrementation)
        }
    }

    private fun si(event: MotionEvent, d: Double): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE ->
                this.counterViewModel.beginIncrementation(d)

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL ->
                this.counterViewModel.endIncrementation()
        }
        return true // True if the listener has consumed the event
    }
}