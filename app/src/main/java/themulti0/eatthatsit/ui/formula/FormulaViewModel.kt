package themulti0.eatthatsit.ui.formula

import androidx.lifecycle.MutableLiveData

class FormulaViewModel {
    val info: MutableLiveData<FormulaInformation> = MutableLiveData()
    val result: MutableLiveData<Double> = MutableLiveData()
}