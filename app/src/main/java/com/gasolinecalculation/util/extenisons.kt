package com.gasolinecalculation.util

import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Replace
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

fun Navigator.setLaunchScreen(screen: SupportAppScreen) {
    applyCommands(
        arrayOf(
            BackTo(null),
            Replace(screen)
        )
    )
}

fun Any.objectScopeName() = "${javaClass.simpleName}_${hashCode()}"

fun Fragment.showSnackMessage(message: String) {
    view?.showSnackMessage(message)
}

fun View.showSnackMessage(message: String) {
    val ssb = SpannableStringBuilder().apply {
        append(message)
        setSpan(
            ForegroundColorSpan(Color.WHITE),
            0,
            message.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    Snackbar.make(this, ssb, Snackbar.LENGTH_LONG).show()
}

suspend fun <T> awaitTaskCompletable(task: Task<T>?): Unit = suspendCoroutine { continuation ->
    task?.addOnCompleteListener {
        if (task.isSuccessful) {
            continuation.resume(Unit)
        } else {
            continuation.resumeWithException(task.exception!!)
        }
    }
}
