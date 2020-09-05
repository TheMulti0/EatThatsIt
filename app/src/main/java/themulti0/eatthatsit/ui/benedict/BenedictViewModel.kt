package themulti0.eatthatsit.ui.benedict

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import java.text.DecimalFormat

class BenedictViewModel(
    private val palSubscription: (Double) -> Unit
) : BaseObservable() {

    private val format: DecimalFormat = DecimalFormat("##.##")

    var pal: Double = 1.5
        @Bindable
        get
        set(value) {
            if (field != value) {
                field = format.format(value).toDouble()

                notifyPropertyChanged(BR.pal)
                palSubscription(field)
            }
        }
}