package com.gasolinecalculation.domain.interactors

import timber.log.Timber
import javax.inject.Inject

class CalculationInteractor @Inject constructor(
) {
    fun loadData() {
        Timber.v("LOADING DATA")
    }
}