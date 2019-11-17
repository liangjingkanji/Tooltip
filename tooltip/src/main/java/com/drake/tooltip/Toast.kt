package com.drake.tooltip

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment


object ToastConfig {
    internal var toast: Toast? = null

    internal var onLevel: (Toast.(msg: CharSequence, level: Int) -> View?)? = null

    fun onLevel(block: Toast.(msg: CharSequence, level: Int) -> View?) {
        onLevel = block
    }
}

fun Context.toast(msg: Int, config: Toast.() -> Unit = {}) {
    ToastConfig.toast?.cancel()

    runMain {
        ToastConfig.toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT).apply { config() }
        ToastConfig.toast?.show()
    }
}

fun Fragment.toast(msg: Int, config: Toast.() -> Unit = {}) {

    activity ?: return

    ToastConfig.toast?.cancel()

    runMain {
        ToastConfig.toast = Toast.makeText(activity, msg, Toast.LENGTH_SHORT).apply { config() }
        ToastConfig.toast?.show()
    }
}

fun Context.toast(msg: CharSequence, config: Toast.() -> Unit = {}) {
    ToastConfig.toast?.cancel()

    runMain {
        ToastConfig.toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT).apply { config() }
        ToastConfig.toast?.show()
    }
}

fun Fragment.toast(msg: CharSequence, config: Toast.() -> Unit = {}) {
    activity ?: return
    ToastConfig.toast?.cancel()

    runMain {
        ToastConfig.toast = Toast.makeText(activity, msg, Toast.LENGTH_SHORT).apply { config() }
        ToastConfig.toast?.show()
    }
}

fun Context.toast(msg: CharSequence, level: Int) {
    ToastConfig.toast?.cancel()

    runMain {
        var levelOfView: View?

        val toast = Toast(this).apply {
            levelOfView = ToastConfig.onLevel?.invoke(this, msg, level)
            view = levelOfView
        }

        levelOfView ?: return@runMain

        ToastConfig.toast = toast
        ToastConfig.toast?.show()
    }
}

fun Fragment.toast(msg: CharSequence, level: Int) {
    activity ?: return
    ToastConfig.toast?.cancel()

    runMain {
        var levelOfView: View?

        val toast = Toast(activity).apply {
            levelOfView = ToastConfig.onLevel?.invoke(this, msg, level)
            view = levelOfView
        }

        levelOfView ?: return@runMain

        ToastConfig.toast = toast
        ToastConfig.toast?.show()
    }
}

fun Context.toast(config: (Toast.() -> View)? = null) {
    ToastConfig.toast?.cancel()

    runMain {
        ToastConfig.toast = Toast(this).apply {
            view = config?.invoke(this)
            duration = Toast.LENGTH_SHORT
        }
        ToastConfig.toast?.show()
    }
}

fun Fragment.toast(config: (Toast.() -> View)? = null) {
    activity ?: return
    ToastConfig.toast?.cancel()

    runMain {
        ToastConfig.toast = Toast(activity).apply {
            view = config?.invoke(this)
            duration = Toast.LENGTH_SHORT
        }
        ToastConfig.toast?.show()
    }
}

fun Context.longToast(msg: Int, config: Toast.() -> Unit = {}) {
    ToastConfig.toast?.cancel()

    runMain {
        ToastConfig.toast = Toast.makeText(this, msg, Toast.LENGTH_LONG).apply { config() }
        ToastConfig.toast?.show()
    }
}

fun Fragment.longToast(msg: Int, config: Toast.() -> Unit = {}) {
    activity ?: return
    ToastConfig.toast?.cancel()

    runMain {
        ToastConfig.toast = Toast.makeText(activity, msg, Toast.LENGTH_LONG).apply { config() }
        ToastConfig.toast?.show()
    }
}

fun Context.longToast(msg: CharSequence, config: Toast.() -> Unit = {}) {
    ToastConfig.toast?.cancel()

    runMain {
        ToastConfig.toast = Toast.makeText(this, msg, Toast.LENGTH_LONG).apply { config() }
        ToastConfig.toast?.show()
    }
}

fun Fragment.longToast(msg: CharSequence, config: Toast.() -> Unit = {}) {
    activity ?: return
    ToastConfig.toast?.cancel()

    runMain {
        ToastConfig.toast = Toast.makeText(activity, msg, Toast.LENGTH_LONG).apply { config() }
        ToastConfig.toast?.show()
    }
}

fun Context.longToast(content: View, config: Toast.(v: View) -> Unit = {}) {
    ToastConfig.toast?.cancel()

    runMain {
        ToastConfig.toast = Toast(this).apply {
            view = content
            duration = Toast.LENGTH_LONG
            config(view)
        }
        ToastConfig.toast?.show()
    }
}

fun Fragment.longToast(content: View, config: Toast.(v: View) -> Unit = {}) {
    activity ?: return
    ToastConfig.toast?.cancel()

    runMain {
        ToastConfig.toast = Toast(activity).apply {
            view = content
            duration = Toast.LENGTH_LONG
            config(view)
        }
        ToastConfig.toast?.show()
    }
}


fun runMain(block: () -> Unit) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        block()
    } else {
        Handler(Looper.getMainLooper()).post { block() }
    }
}
