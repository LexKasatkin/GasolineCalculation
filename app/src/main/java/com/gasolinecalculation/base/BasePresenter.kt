package com.gasolinecalculation.base

import com.gasolinecalculation.system.DispatchersProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import moxy.MvpPresenter
import moxy.MvpView
import kotlin.coroutines.CoroutineContext


abstract class BasePresenter<V : MvpView>(dispatchers: DispatchersProvider) : MvpPresenter<V>(),
    CoroutineScope by CoroutineScope(dispatchers.main()) {

    /**
     * Handler for proceed exception
     */
    private val handler = CoroutineExceptionHandler { _, throwable ->
        proceedCoroutineError(throwable)
    }

    /**
     * Need to proceed error in individual presenter for avoiding missing processing
     * errors.
     */
    abstract fun proceedCoroutineError(throwable: Throwable)

    /**
     * A failure or cancellation of a child does not cause the supervisor
     * job to fail and does not affect its other children.
     */
    private val coroutineJob = SupervisorJob()

    /**
     * In most cases, it’s preferable to create your scope with the Main dispatcher
     * which results in simpler code and less explicit context switching.
     */
    override val coroutineContext: CoroutineContext = dispatchers.main() + coroutineJob + handler


    override fun onDestroy() {
        coroutineContext.cancelChildren()
    }


}
