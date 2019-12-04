package themulti0.eatthatsit

import org.junit.Test

import org.junit.Assert.*
import themulti0.eatthatsit.services.benedictformula.BenedictFormulaService
import themulti0.eatthatsit.services.benedictformula.bmr.HarrisBenedictFormula
import themulti0.eatthatsit.services.benedictformula.bmr.IBenedictBmrFormula
import themulti0.eatthatsit.services.benedictformula.bmr.MiffinStJeorBenedictFormula
import themulti0.eatthatsit.services.benedictformula.bmr.RozaShizgalBenedictFormula
import themulti0.eatthatsit.services.benedictformula.models.*
import kotlin.math.roundToInt

class BenedictFormulaTest {

    private val pal: Double = 1.755 // Physical activity level

    private val metricMale: Person = Person(
        Gender.Male,
        Weight(78.0, WeightVolume.Kilogram),
        Length(180.0, LengthVolume.Centimeter),
        24.0
    )
    private val imperialMale: Person = Person(
        Gender.Male,
        Weight(171.9606, WeightVolume.Pound),
        Length(70.86614, LengthVolume.Inch),
        24.0
    )

    private val metricFemale: Person = Person(
        Gender.Female,
        Weight(54.0, WeightVolume.Kilogram),
        Length(170.0, LengthVolume.Centimeter),
        20.0
    )
    private val imperialFemale: Person = Person(
        Gender.Female,
        Weight(119.0496, WeightVolume.Pound),
        Length(66.92913, LengthVolume.Inch),
        20.0
    )

    private lateinit var benedict: BenedictFormulaService

    @Test
    fun harrisBenedict() {
        validateAllPossibilites(
            3295,
            3281,
            2440,
            2447,
            HarrisBenedictFormula())
    }

    @Test
    fun rozaShizgal() {
        validateAllPossibilites(
            3267,
            3260,
            2434,
            2430,
            RozaShizgalBenedictFormula()
        )
    }

    @Test
    fun miffinStJr() {
        validateAllPossibilites(
            3141,
            3140,
            2354,
            2350,
            MiffinStJeorBenedictFormula()
        )
    }

    private fun validateAllPossibilites(
        maleMetricExpectedCalories: Int,
        maleImperialExpectedCalories: Int,
        femaleMetricExpectedCalories: Int,
        femaleImperialExpectedCalories: Int,
        formula: IBenedictBmrFormula
    ) {
        validateFormula(maleMetricExpectedCalories, formula, metricMale, "Male Metric")
        validateFormula(maleImperialExpectedCalories, formula, imperialMale, "Male Imperial")

        validateFormula(femaleMetricExpectedCalories, formula, metricFemale, "Female Metric")
        validateFormula(femaleImperialExpectedCalories, formula, imperialFemale, "Female Imperial")
    }

    private fun validateFormula(
        expectedCalories: Int,
        formula: IBenedictBmrFormula,
        person: Person,
        message: String
    ) {
        benedict = BenedictFormulaService(formula)
        val calories: Int = benedict.calculate(
            person,
            pal
        ).roundToInt().floorFirstDigit()
        assertTrue(
            "Calculated calorie amount for ${formula.javaClass.canonicalName}, $message is wrong!",
            expectedCalories.floorFirstDigit() == calories.floorFirstDigit()
        )
    }

    private fun Int.floorFirstDigit(): Int
        = this / 10 * 10

}
