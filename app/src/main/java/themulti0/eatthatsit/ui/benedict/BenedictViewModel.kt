package themulti0.eatthatsit.ui.benedict

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BenedictViewModel : ViewModel() {
    var inputWeight: Double = 0.0
    var inputHeight: Double = 0.0
    var inputAgeInYears: Double = 0.0
    var inputPal: Double = 0.0

    var result: MutableLiveData<String> = MutableLiveData("hi me pipia")
}