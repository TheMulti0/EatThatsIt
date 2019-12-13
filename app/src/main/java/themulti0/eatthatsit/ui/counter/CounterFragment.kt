package themulti0.eatthatsit.ui.counter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.counter_fragment.*
import themulti0.eatthatsit.R
import themulti0.eatthatsit.databinding.CounterFragmentBinding


class CounterFragment : Fragment() {

    private lateinit var counterName: String
    private lateinit var vm: CounterViewModel

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
            counterName = readCounterNameAttribute(context, attrs)
        }
        catch (e: Exception) { }
    }

    private fun readCounterNameAttribute(context: Context, attrs: AttributeSet): String {
        val obtainedAttributes =
            context.obtainStyledAttributes(attrs, R.styleable.CounterFragment)

        val counterName = obtainedAttributes
            .getText(R.styleable.CounterFragment_counter_name)
            .toString()

        obtainedAttributes.recycle()

        return counterName
    }

    override fun onViewCreated(view: View, savedInstanceBundle: Bundle?) {
        super.onViewCreated(view, savedInstanceBundle)

        vm = CounterViewModel(
            CounterDatabase(
                context!!.getSharedPreferences(
                    counterName,
                    Context.MODE_PRIVATE)),
            counterName)

        val binding = CounterFragmentBinding.bind(view)
        binding.lifecycleOwner = this
        binding.vm = vm

        val incrementation = 1.0
        minus_button.setOnTouchListener { _, event ->
            handleTouchEvent(event, -incrementation)
        }
        plus_button.setOnTouchListener { _, event ->
            handleTouchEvent(event, incrementation)
        }

        subtract_button.setOnClickListener {
            sendInputDialog(it, "Subtract") { d -> -d }
        }
        append_button.setOnClickListener {
            sendInputDialog(it, "Append") { d -> d }
        }
    }

    private fun sendInputDialog(it: View, action: String, doubleConverter: (Double) -> Double) {
        val context: Context = it.context

        val input = EditText(context)
        input.inputType = 8194

        AlertDialog.Builder(context)
            .setTitle(action)
            .setView(input)
            .setPositiveButton(action) { _, _ -> positiveClickCallback(input, doubleConverter) }
            .setNegativeButton("Cancel") { dialog, _ -> negativeClickCallback(dialog) }
            .show()
    }

    private fun positiveClickCallback(input: EditText, doubleConverter: (Double) -> Double) {
        val text: Double = input.text.toString().toDoubleOrNull() ?: return
        vm.incrementDouble(doubleConverter(text))
    }

    private fun negativeClickCallback(dialog: DialogInterface) = dialog.cancel()

    private fun handleTouchEvent(event: MotionEvent, incrementation: Double): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE ->
                this.vm.startIncrementation(incrementation)

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                this.vm.stopIncrementation()
                this.vm.incrementDouble(incrementation) // In event of a click
            }

        }
        return true // True if the listener has consumed the event
    }
}