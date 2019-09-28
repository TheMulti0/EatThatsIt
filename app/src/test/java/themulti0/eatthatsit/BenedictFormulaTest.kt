package themulti0.eatthatsit

import org.junit.Test

import org.junit.Assert.*
import themulti0.eatthatsit.services.benedictFormula.BenedictFormulaService
import themulti0.eatthatsit.services.benedictFormula.bmr.HarrisBenedictFormula
import themulti0.eatthatsit.services.benedictFormula.models.*
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
        val maleCalories = 3295
        harrisBenedict_male_metric(maleCalories)
        harrisBenedict_male_imperial(maleCalories)

        val femaleCalories = 2444
        harrisBenedict_female_metric(femaleCalories)
        harrisBenedict_female_imperial(femaleCalories + 2) // Not exactly accurate because of conversion between metric to imperial units
    }

    fun harrisBenedict_male_metric(expectedCalories: Int) {
        validateFormula(
            expectedCalories,
            HarrisBenedictFormula(),
            metricMale,
            "Harris-Benedict male metric"
        )
    }

    fun harrisBenedict_male_imperial(expectedCalories: Int) {
        validateFormula(
            expectedCalories,
            HarrisBenedictFormula(),
            metricMale,
            "Harris-Benedict male imperial"
        )
    }

    fun harrisBenedict_female_metric(expectedCalories: Int) {
        validateFormula(
            expectedCalories,
            HarrisBenedictFormula(),
            metricFemale,
            "Harris-Benedict female metric"
        )
    }

    fun harrisBenedict_female_imperial(expectedCalories: Int) {
        validateFormula(
            expectedCalories,
            HarrisBenedictFormula(),
            imperialFemale,
            "Harris-Benedict female imperial"
        )
    }

    private fun validateFormula(
        expectedCalories: Int,
        formula: HarrisBenedictFormula,
        person: Person,
        message: String
    ) {
        benedict = BenedictFormulaService(formula)

        val calories: Int = benedict.calculate(person, pal).roundToInt()
        assertTrue(
            "Calculated calorie amount for $message is wrong!",
            calories == expectedCalories
        )
    }
}
