package com.gasolinecalculation.system

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Interface for providing of [CoroutineDispatcher].
 */
interface DispatchersProvider {
    fun main(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
    fun default(): CoroutineDispatcher
    fun unconfined(): CoroutineDispatcher
}