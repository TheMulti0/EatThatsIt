package themulti0.eatthatsit

import org.junit.Test

import org.junit.Assert.*
import themulti0.eatthatsit.services.benedictFormula.BenedictFormulaService
import themulti0.eatthatsit.services.benedictFormula.bmr.HarrisBenedictFormula
import themulti0.eatthatsit.services.benedictFormula.models.*
import kotlin.math.roundToInt

class BenedictFormulaTest {

    private val pal: Double = 1.755
    private val person: Person = Person(
        Gender.Male,
        Weight(61.0, WeightVolume.Kilogram),
        Length(171.0, LengthVolume.Centimeter),
        14.0
    )


    private lateinit var benedict: BenedictFormulaService

    @Test
    fun harrisBenedict() {
        val expectedCalories: Int = 2924

        benedict = BenedictFormulaService(HarrisBenedictFormula())

        val calories: Double = benedict.calculate(person, pal)
        assertTrue("Calculated calorie amount is wrong!", calories.roundToInt() == expectedCalories)
    }
}
