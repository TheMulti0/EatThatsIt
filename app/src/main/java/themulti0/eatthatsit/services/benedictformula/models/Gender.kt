package themulti0.eatthatsit.services.benedictformula.models

enum class Gender(val value: Int) {
    Male(0),
    Female(1);

    companion object {
        fun fromInt(value: Int) = values().first { it.value == value }
    }
}