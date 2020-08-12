package com.gasolinecalculation.ui.calculation.model

import com.gasolinecalculation.domain.model.RefuelDomain

/**
 * Presentation model for refuel.
 */
data class Refuel(
    val cost: String = "",
    val mileage: String = "",
    val dateTime: String = "",
    val allCost: String = ""
)

/**
 * Converts [RefuelDomain] to [Refuel].
 */
fun RefuelDomain.toPresentation(
    costFormat: String,
    mileageFormat: String,
    dateTimePattern: String,
    currency: String
) = Refuel(
    cost = costFormat.format(cost),
    mileage = mileageFormat.format(mileage),
    dateTime = dateTime.toString(dateTimePattern),
    allCost = "${(cost * mileage)} $currency"

)
