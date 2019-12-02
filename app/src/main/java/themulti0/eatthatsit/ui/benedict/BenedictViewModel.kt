package themulti0.eatthatsit.ui.benedict

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import themulti0.eatthatsit.services.benedictFormula.models.Gender

class BenedictViewModel : ViewModel() {
    var inputGender: Gender = Gender.Male
    var inputWeight: Double = 0.0
    var inputHeight: Double = 0.0
    var inputAgeInYears: Double = 0.0
    var inputPal: Double = 0.0

    var result: MutableLiveData<String> = MutableLiveData("Result is empty.")
}