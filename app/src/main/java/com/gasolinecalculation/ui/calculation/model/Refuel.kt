package com.gasolinecalculation.ui.calculation.model

import com.gasolinecalculation.data.model.Refuel

/**
 * Presentation model for refuel.
 */
data class Refuel(
    val cost: Int = 0,
    val mileage: Int = 0,
    val dateTime: String = ""
)

/**
 * Converts [Refuel] to [Refuel].
 */
fun Refuel.toPresentation() = Refuel(
    cost = cost,
    mileage = mileage,
    dateTime = dateTime.toString()
)
