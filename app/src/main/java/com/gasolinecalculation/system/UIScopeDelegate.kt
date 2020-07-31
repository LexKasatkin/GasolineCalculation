package com.gasolinecalculation.system

import android.os.Bundle
import androidx.fragment.app.Fragment
import moxy.MvpAppCompatDialogFragment
import moxy.MvpAppCompatFragment
import timber.log.Timber
import toothpick.Toothpick
import kotlin.reflect.KProperty


/**
 * Класс-делегат предназначен для однократной инициализации UI скоупов Toothpick
 * при создании и восстановлени фрагментов.
 *
 * Проблема в следующем.
 * Скоуп должен быть инициализиорован в момент создания фрагмента, в методе Fragment.onCreate()
 * Потому что это является основной точкой входа в экран.
 *
 * Проблема в том, что у фрагментов этот метод вызывается всегда, не только при создании но и при восстановлении.
 * А нам надо проинициализировать скоуп только один раз.
 *
 * Стратегия следующая: добавляем в аргументы фрагмента параметр ARG_SCOPE_NAME
 * Если его ещё нет - это значит, что фрагмент создаётся первый раз, и нужно инициализировать скоуп
 * и тут же сохраняем ARG_SCOPE_NAME в аргументы фрагмента
 *
 * Если ARG_SCOPE_NAME уже есть - это значит, что происходит восстановление фрагмента,
 * например была смена конфигурации (переворот экрана). В этом случае скоуп уже проинициализирован
 * и ничего делать не надо.
 *
 * Но при убийстве процесса системой, при восстановлении фрагментов, аргумент ARG_SCOPE_NAME будет присутствовать,
 * но скоуп не будет инициализирован, поэтому если аргумент установлен, мы проверяем не открыт ли скоуп, если не
 * открыт то инициализируем.
 *
 * Сохранение имени скоупа в аргументы фрагмента ещё нужно для динамических скоупов, когда мы генерируем имя скоупа при
 * открытии фрагмента первый раз.
 *
 * Класс жёстко связан с Moxy и Mvp фрагментами. UiScopeDelegate следит за жизненным циклом Mvp-делагата
 * и при onDestroy() закрывает UI-скоуп
 *
 * @author Roman Savelev (aka fantom and rsajob). Date: 23.10.18
 */

private const val LOG_TAG = "Toothpick"
private const val ARG_SCOPE_NAME = "arg_scope_name"

class UiScopeDelegate<MvpFragment : Fragment>(
    private val initScopeName: String,
    private val fragment: MvpFragment,
    mvpDelegate: MvpDelegateObservable<in MvpFragment>,
    private val initScope: ((String) -> Unit)? = null
) {
    private var realScopeName: String? = null

    init {
        mvpDelegate.lifecycleObserver = object : MvpDelegateObservable.LifecycleObserver {
            override fun onDestroy() {
                Timber.i("Destroy UI scope $realScopeName")
                closeScope()
            }
        }
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        var scopeName = fragment.arguments?.getString(ARG_SCOPE_NAME)
        if (scopeName == null) {
            scopeName = initScopeName
            // Save scope name to fragment arguments
            fragment.arguments = (fragment.arguments ?: Bundle()).apply { putString(ARG_SCOPE_NAME, scopeName) }
        }

        if (!Toothpick.isScopeOpen(scopeName)) {
            initScope?.invoke(scopeName)
        }

        this.realScopeName = scopeName
        return scopeName
    }


    fun closeScope() {
        if (realScopeName != null) {
            Timber.i("Close UI scope $realScopeName")
            Toothpick.closeScope(realScopeName)
        }
    }

}

fun uniqueScopeName(baseScopeName: String): String = "${baseScopeName}_${System.currentTimeMillis()}"

fun MvpAppCompatFragment.initUiScope(scopeName: String, initScope: ((String) -> Unit)?) =
    UiScopeDelegate(scopeName, this, this.mvpDelegate, initScope)

fun MvpAppCompatFragment.initDynamicUiScope(baseScopeName: String, initScope: ((String) -> Unit)?) =
    UiScopeDelegate(
        uniqueScopeName(baseScopeName),
        this,
        this.mvpDelegate,
        initScope
    )

fun MvpAppCompatFragment.initDynamicUiScope(initScope: ((String) -> Unit)?) =
    UiScopeDelegate(
        uniqueScopeName(this::class.java.simpleName),
        this,
        this.mvpDelegate,
        initScope
    )

fun MvpAppCompatDialogFragment.initUiScope(scopeName: String, initScope: ((String) -> Unit)?) =
    UiScopeDelegate(scopeName, this, this.mvpDelegate, initScope)

fun MvpAppCompatDialogFragment.initDynamicUiScope(baseScopeName: String, initScope: ((String) -> Unit)?) =
    UiScopeDelegate(
        uniqueScopeName(baseScopeName),
        this,
        this.mvpDelegate,
        initScope
    )

fun MvpAppCompatDialogFragment.initDynamicUiScope(initScope: ((String) -> Unit)?) =
    UiScopeDelegate(
        uniqueScopeName(this::class.java.simpleName),
        this,
        this.mvpDelegate,
        initScope
    )