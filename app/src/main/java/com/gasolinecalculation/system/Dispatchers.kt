package com.gasolinecalculation.system

import kotlinx.coroutines.Dispatchers

class Dispatchers : DispatchersProvider {
    override fun main() = Dispatchers.Main
    override fun io() = Dispatchers.IO
    override fun default() = Dispatchers.Default
    override fun unconfined() = Dispatchers.Unconfined
}
