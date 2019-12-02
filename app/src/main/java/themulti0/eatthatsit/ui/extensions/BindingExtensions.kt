package themulti0.eatthatsit.ui.extensions

import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.core.widget.doAfterTextChanged

fun <T : Any> Spinner.bindToAdapter(
    itemSelectedListener: AdapterView.OnItemSelectedListener,
    arrayAdapter: ArrayAdapter<T>
) {
    this.onItemSelectedListener = itemSelectedListener

    arrayAdapter.also { adapter ->
        adapter.setDropDownViewResource(android.R.layout.select_dialog_item)
        this.adapter = adapter
    }
}

fun EditText.bindToViewModel(propertySetter: (String) -> Unit): Unit {
    this.doAfterTextChanged { text ->
        try {
            propertySetter(text.toString())
        } catch (e: NumberFormatException) {
        }
    }
}