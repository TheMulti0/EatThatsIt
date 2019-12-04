package themulti0.eatthatsit.ui.numberCounter

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.room.Room
import kotlinx.android.synthetic.main.counter_fragment.*
import themulti0.eatthatsit.R
import themulti0.eatthatsit.database.AppDatabase
import themulti0.eatthatsit.databinding.CounterFragmentBinding


class CounterFragment : Fragment() {

    private lateinit var database: AppDatabase
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

        database = getDatabase(context)

    }

    private fun getDatabase(context: Context): AppDatabase {
        val databaseClass: Class<AppDatabase> = AppDatabase::class.java
        return Room
            .databaseBuilder(context, databaseClass, "eatthatsit.db")
            .fallbackToDestructiveMigration() // Automatically remove older database schemas when version changes
            .allowMainThreadQueries()
            .build()
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

    override fun onViewCreated(view: View, savedInstanceBundle: Bundle?): Unit {
        super.onViewCreated(view, savedInstanceBundle)

        vm = initializeViewModel()
        vm.counterName = counterName

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
    }

    private fun initializeViewModel(): CounterViewModel
        = CounterViewModel(database.nutrtionDao())

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