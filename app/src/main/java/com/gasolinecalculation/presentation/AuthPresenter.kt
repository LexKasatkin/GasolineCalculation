package com.gasolinecalculation.presentation

import com.gasolinecalculation.base.BasePresenter
import com.gasolinecalculation.navigation.FlowRouter
import com.gasolinecalculation.system.DispatchersProvider
import moxy.InjectViewState
import org.xml.sax.ErrorHandler
import javax.inject.Inject


@InjectViewState
class AuthPresenter @Inject constructor(
    private val dispatchersProvider: DispatchersProvider,
    private val router: FlowRouter,
    private val errorHandler: ErrorHandler
) : BasePresenter<AuthView>(dispatchersProvider) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }


    fun onBackPressed() = router.exit()

    fun login(email: String, password: String) {
//        viewState.showProgress(true)
//        router.newRootFlow(Screens.DrawerFlow)
//        viewState.showProgress(false)
    }

    override fun proceedCoroutineError(throwable: Throwable) {

    }

//    override fun proceedCoroutineError(throwable: Throwable) {
//        errorHandler.proceedError(throwable)
//    }
}
