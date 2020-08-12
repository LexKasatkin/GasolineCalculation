package com.gasolinecalculation.domain.model

import org.joda.time.DateTime

/**
 * Refuel model of domain layer.
 */
data class RefuelDomain(
    val cost: Float = 0F,
    val mileage: Int = 0,
    val dateTime: DateTime
)