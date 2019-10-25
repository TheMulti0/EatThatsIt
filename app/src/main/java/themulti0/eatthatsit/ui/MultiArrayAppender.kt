package themulti0.eatthatsit.ui

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.view.LayoutInflater



class MultiArrayAppender(
    private val givenContext: Context,
    private val resource: Int,
    shortList: Array<out String>,
    private val longList: Array<out String> ) // Short list is the default
    : ArrayAdapter<String>(givenContext, resource, shortList) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = givenContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val row = inflater.inflate(this.resource, parent, false) as TextView
        row.text = longList[position]

        return row
    }

}