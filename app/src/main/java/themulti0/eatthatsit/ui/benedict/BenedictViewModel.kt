package themulti0.eatthatsit.ui.benedict

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class BenedictViewModel(
    private val palSubscription: () -> Unit
) : BaseObservable() {

    var pal: String = "1.5"
        @Bindable
        get
        set(value) {
            if (field != value) {
                field = value

                notifyPropertyChanged(BR.pal)
                palSubscription()
            }
        }
}