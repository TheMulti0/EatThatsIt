package themulti0.eatthatsit.services.benedictformula.bmr

import themulti0.eatthatsit.services.benedictformula.models.Person

fun IBenedictBmrFormula.calculateBmr(
    person: Person,
    weightMultiplier: Double,
    heightMultiplier: Double,
    ageMultiplier: Double,
    weightAccessor: (Person) -> Double = this::anyWeightAccessor,
    heightAccessor: (Person) -> Double = this::anyHeightAccessor
): Double =

    (weightMultiplier * weightAccessor(person)) +
            (heightMultiplier * heightAccessor(person)) -
            (ageMultiplier * person.age)

fun IBenedictBmrFormula.anyWeightAccessor(person: Person): Double = person.weight.amount

fun IBenedictBmrFormula.metricWeightAccessor(person: Person): Double =
    person.weight.amount * person.weight.volume.valueOfOneInKg

fun IBenedictBmrFormula.anyHeightAccessor(person: Person): Double = person.height.amount

fun IBenedictBmrFormula.metricHeightAccessor(person: Person): Double =
    person.height.amount * person.height.volume.valueOfOneInCm