package com.drake.tooltip

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast

private var toast: Toast? = null


fun Context.toast(message: Int, config: Toast.() -> Unit = {}) {
    toast?.cancel()

    runMain {
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT).apply { config() }
        toast?.show()
    }
}

fun Context.toast(message: CharSequence, config: Toast.() -> Unit = {}) {
    toast?.cancel()

    runMain {
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT).apply { config() }
        toast?.show()
    }
}

fun Context.toast(content: View, config: Toast.(v: View) -> Unit = {}) {
    toast?.cancel()

    runMain {
        toast = Toast(this).apply {
            view = content
            duration = Toast.LENGTH_SHORT
            config(view)
        }
        toast?.show()
    }
}

fun Context.longToast(message: Int, config: Toast.() -> Unit = {}) {
    toast?.cancel()

    runMain {
        toast = Toast.makeText(this, message, Toast.LENGTH_LONG).apply { config() }
        toast?.show()
    }
}

fun Context.longToast(message: CharSequence, config: Toast.() -> Unit = {}) {
    toast?.cancel()

    runMain {
        toast = Toast.makeText(this, message, Toast.LENGTH_LONG).apply { config() }
        toast?.show()
    }
}

fun Context.longToast(content: View, config: Toast.(v: View) -> Unit = {}) {
    toast?.cancel()

    runMain {
        toast = Toast(this).apply {
            view = content
            duration = Toast.LENGTH_LONG
            config(view)
        }
        toast?.show()
    }
}


private fun runMain(block: () -> Unit) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        block()
    } else {
        Handler(Looper.getMainLooper()).post { block() }
    }
}
