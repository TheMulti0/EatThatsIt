package themulti0.eatthatsit.ui.extensions

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHolder<TView : View>(val view: TView) : RecyclerView.ViewHolder(view)

class RecyclerViewAdapter<TView : View, TData>(
    private var data: MutableList<TData>,
    private val viewFactory: (ViewGroup) -> TView,
    private val dataConsumer: (TView, TData) -> Unit
) :
    RecyclerView.Adapter<RecyclerViewHolder<TView>>() {

    private val views: MutableList<RecyclerViewHolder<TView>> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder<TView> {
        val viewHolder = RecyclerViewHolder(viewFactory(parent))

        views.add(viewHolder)

        return viewHolder
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerViewHolder<TView>, position: Int) {
        dataConsumer(holder.view, data[position])
    }

    fun refreshData(newData: MutableList<TData>) {
        for ((index, holder) in views.withIndex()) {
            dataConsumer(holder.view, newData[index])
        }
        data = newData
    }

}