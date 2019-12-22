package themulti0.eatthatsit.ui.benedict

enum class BenedictFormulaType(
    val index: Int,
    val formulaName: String
) {
    Average(0, "Average"),
    HarrisBenedict(1, "Harris - Benedict"),
    RozaShizgal(2, "Roza - Shizgal"),
    MiffinStJeor(3, "Miffin - St.Jeor");

    companion object {
        fun fromInt(value: Int) = values().first { it.index == value }
    }
}