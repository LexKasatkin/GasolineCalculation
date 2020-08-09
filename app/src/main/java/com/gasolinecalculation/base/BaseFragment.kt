package com.gasolinecalculation.base

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gasolinecalculation.di.DI
import com.gasolinecalculation.ui.global.ProgressDialog
import com.gasolinecalculation.util.objectScopeName
import com.gasolinecalculation.util.showSnackMessage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import moxy.MvpAppCompatFragment
import timber.log.Timber
import toothpick.Scope
import toothpick.Toothpick

private const val PROGRESS_TAG = "bf_progress"
private const val STATE_SCOPE_NAME = "state_scope_name"

abstract class BaseFragment : MvpAppCompatFragment() {
    abstract val layoutRes: Int

    protected var auth: FirebaseAuth? = null
    protected var currentUser: FirebaseUser? = null

    private var instanceStateSaved: Boolean = false

    private val viewHandler = Handler()

    protected open val parentScopeName: String by lazy {
        (parentFragment as? BaseFragment)?.fragmentScopeName
            ?: DI.APP_SCOPE
    }

    private lateinit var fragmentScopeName: String
    protected lateinit var scope: Scope
        private set

    protected open fun installModules(scope: Scope) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        fragmentScopeName = savedInstanceState?.getString(STATE_SCOPE_NAME) ?: objectScopeName()

        if (Toothpick.isScopeOpen(fragmentScopeName)) {
            Timber.d("Get exist UI scope: $fragmentScopeName")
            scope = Toothpick.openScope(fragmentScopeName)
        } else {
            Timber.d("Init new UI scope: $fragmentScopeName -> $parentScopeName")
            scope = Toothpick.openScopes(parentScopeName, fragmentScopeName)
            installModules(scope)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(layoutRes, container, false)

    override fun onResume() {
        super.onResume()
        instanceStateSaved = false
    }

    override fun onStart() {
        super.onStart()
        currentUser = auth?.currentUser
    }

    // Fix for async views (like swipeToRefresh and RecyclerView)
    // If synchronously call actions on swipeToRefresh in sequence show and hide then swipeToRefresh will not hidden
    protected fun postViewAction(action: () -> Unit) {
        viewHandler.post(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewHandler.removeCallbacksAndMessages(null)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        instanceStateSaved = true
        outState.putString(STATE_SCOPE_NAME, fragmentScopeName)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (needCloseScope()) {
            // Destroy this fragment with scope
            Timber.d("Destroy UI scope: $fragmentScopeName")
            Toothpick.closeScope(scope.name)
        }
    }

    // This is android, baby!
    private fun isRealRemoving(): Boolean =
        (isRemoving && !instanceStateSaved) || // Because isRemoving == true for fragment in backstack on screen rotation
                ((parentFragment as? BaseFragment)?.isRealRemoving() ?: false)

    // It will be valid only for 'onDestroy()' method
    private fun needCloseScope(): Boolean =
        when {
            activity?.isChangingConfigurations == true -> false
            activity?.isFinishing == true -> true
            else -> isRealRemoving()
        }

    protected fun showProgressDialog(progress: Boolean) {
        if (!isAdded || instanceStateSaved) return

        val fragment = childFragmentManager.findFragmentByTag(PROGRESS_TAG)
        if (fragment != null && !progress) {
            (fragment as? ProgressDialog)?.dismissAllowingStateLoss()
            childFragmentManager.executePendingTransactions()
        } else if (fragment == null && progress) {
            ProgressDialog().show(childFragmentManager, PROGRESS_TAG)
            childFragmentManager.executePendingTransactions()
        }
    }

    fun showMessage(message: String) {
        showSnackMessage(message)
    }

    open fun onBackPressed() {}
}