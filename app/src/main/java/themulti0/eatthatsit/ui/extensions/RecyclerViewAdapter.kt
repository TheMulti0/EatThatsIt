package themulti0.eatthatsit.ui.extensions

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

typealias Holder<TView> = RecyclerViewAdapter.RecyclerViewHolder<TView>

class RecyclerViewAdapter<TView : View, TData>(
    private val data: List<TData>,
    private val viewFactory: (ViewGroup) -> TView,
    private val dataSetter: (TView, TData) -> Unit
) :
    RecyclerView.Adapter<Holder<TView>>() {

    class RecyclerViewHolder<TView : View>(val view: TView) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder<TView> =
        RecyclerViewHolder(viewFactory(parent))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: Holder<TView>, position: Int) {
        dataSetter(holder.view, data[position])
    }

}