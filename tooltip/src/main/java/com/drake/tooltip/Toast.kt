package com.drake.tooltip

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment


fun Context.toast(msg: Int) {
    ToastConfig.toast?.cancel()
    showDefault(getString(msg))
}


fun Fragment.toast(msg: Int) {
    context?.toast(msg)
}

fun Context.toast(msg: CharSequence) {
    ToastConfig.toast?.cancel()
    showDefault(msg)
}

fun Fragment.toast(msg: CharSequence) {
    context?.toast(msg)
}

/**
 * 在函数[ToastConfig.onToast]通过参数[level]来判断返回不同的View
 */
fun Context.toast(msg: CharSequence, level: Int) {

    ToastConfig.toast?.cancel()

    runMain {
        var levelOfView: View?

        val toast = Toast(this).apply {
            levelOfView = ToastConfig.onLevel?.invoke(this, this@toast, msg, level)
            view = levelOfView
        }

        levelOfView ?: return@runMain

        ToastConfig.toast = toast
        ToastConfig.toast?.show()
    }
}

fun Fragment.toast(msg: CharSequence, level: Int) {
    context?.toast(msg, level)
}

/**
 * 配置吐司
 */
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
    context?.toast(config)
}

fun Context.longToast(msg: Int) {
    ToastConfig.toast?.cancel()
    showDefault(getString(msg), false)
}

fun Fragment.longToast(msg: Int) {
    context?.toast(msg)
}

fun Context.longToast(msg: CharSequence) {
    ToastConfig.toast?.cancel()
    showDefault(msg, false)
}

fun Fragment.longToast(msg: CharSequence) {
    context?.toast(msg)
}


/**
 * 显示常用默认的吐司或者全局设置的吐司样式
 *
 * @param msg 吐司内容
 * @param short 消息停留时间间隔
 */
private fun Context?.showDefault(msg: CharSequence, short: Boolean = true) {
    this ?: return

    runMain {

        ToastConfig.toast = if (ToastConfig.onToast != null) {
            Toast(this).apply {
                duration = if (short) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
                view = ToastConfig.onToast!!(this, this@showDefault, msg)
            }
        } else Toast.makeText(this, msg, Toast.LENGTH_SHORT)

        ToastConfig.toast?.show()
    }
}
