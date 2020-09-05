package themulti0.eatthatsit.ui.extensions

fun format(value: Double, decimalPoints: Int): String =
    "%.${decimalPoints}f".format(value)