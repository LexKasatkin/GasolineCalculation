package com.gasolinecalculation.ui.calculation

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.gasolinecalculation.R
import com.gasolinecalculation.base.BaseFragment
import com.gasolinecalculation.presentation.calculation.CalculationPresenter
import com.gasolinecalculation.presentation.calculation.CalculationView
import com.gasolinecalculation.ui.calculation.model.RefuelItem
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import kotlinx.android.synthetic.main.fragment_calculation.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class CalculationFragment : BaseFragment(), CalculationView {
    private var refuelsAdapter: FastItemAdapter<RefuelItem> = FastItemAdapter()

    override val layoutRes = R.layout.fragment_calculation

    @InjectPresenter
    lateinit var presenter: CalculationPresenter

    @ProvidePresenter
    fun providePresenter(): CalculationPresenter =
        scope.getInstance(CalculationPresenter::class.java)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initUI()
    }

    private fun initUI() {
        rvRefuels.layoutManager = LinearLayoutManager(requireContext())
        rvRefuels.adapter = refuelsAdapter
    }
//        view?.addSystemBottomPadding()
//        toolbar.apply {
//            setNavigationOnClickListener { presenter.onBackPressed() }
//            addSystemTopPadding()
//        }
//        btnLogin.setOnClickListener {
//            presenter.login(
//                etEmail.text.toString(),
//                etPassword.text.toString()
//            )
//        }

//    }

    override fun showProgress(isVisible: Boolean) {
        progress.isVisible = isVisible
    }

    override fun showRefuels(refuels: List<RefuelItem>) {
        refuelsAdapter.setNewList(refuels)
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }
}