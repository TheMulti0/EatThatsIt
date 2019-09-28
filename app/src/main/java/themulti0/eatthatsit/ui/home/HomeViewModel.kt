package themulti0.eatthatsit.ui.home

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    var inputWeight: Double = 0.0
    var inputHeight: Double = 0.0
    var inputAgeInYears: Double = 0.0

    var result: MutableLiveData<String> = MutableLiveData("hi me pipia")
}