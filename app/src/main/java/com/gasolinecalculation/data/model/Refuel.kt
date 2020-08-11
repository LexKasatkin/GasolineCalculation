package com.gasolinecalculation.data.model

import java.util.*

data class Refuel(
    val cost: Int = 0,
    val mileage: Int = 0,
    val dateTime: Date? = null,
    val userToken: String? = null
)
