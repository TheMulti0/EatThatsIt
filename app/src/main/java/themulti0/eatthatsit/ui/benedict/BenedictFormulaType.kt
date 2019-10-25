package themulti0.eatthatsit.ui.benedict

enum class BenedictFormulaType(val value: Int) {
    Average(0),
    HarrisBenedict(1),
    RozaShizgal(2),
    MiffinStJeor(3);

    companion object {
        fun fromInt(value: Int) = values().first { it.value == value }
    }
}