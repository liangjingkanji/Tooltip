package com.drake.tooltip

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner


//<editor-fold desc="短吐司">
fun Context.toast(msg: Int) {
    ToastConfig.toast?.cancel()
    showDefault(getString(msg))
}


fun FragmentActivity.toast(msg: Int) {
    applicationContext.toast(msg)
}


fun Fragment.toast(msg: Int) {
    context?.toast(msg)
}

fun Context.toast(msg: CharSequence?) {
    ToastConfig.toast?.cancel()
    showDefault(msg)
}

fun FragmentActivity.toast(msg: CharSequence?) {
    applicationContext.toast(msg)
}

fun Fragment.toast(msg: CharSequence?) {
    context?.toast(msg)
}
//</editor-fold>

//<editor-fold desc="等级吐司">
/**
 * 在函数[ToastConfig.onToast]通过参数[level]来判断返回不同的View
 */
@SuppressLint("ShowToast")
fun Context.toast(msg: CharSequence?, level: Int) {
    msg ?: return
    ToastConfig.toast?.cancel()
    runMain {
        val toast = Toast(this).apply {
            ToastConfig.onLevel?.invoke(this, this@toast, msg, level)
            if (view == null) {
                view = Toast.makeText(this@toast, msg, Toast.LENGTH_SHORT).view
            }
        }
        ToastConfig.toast = toast
        ToastConfig.toast?.show()
    }
}

fun FragmentActivity.toast(msg: CharSequence?, level: Int) {
    applicationContext.toast(msg, level)
}

fun Fragment.toast(msg: CharSequence?, level: Int) {
    context?.toast(msg, level)
}
//</editor-fold>

//<editor-fold desc="自定义视图">
/**
 * 函数参数要求返回一个视图
 */
fun Context.toast(block: Toast.(Context) -> View) {
    ToastConfig.toast?.cancel()
    runMain {
        ToastConfig.toast = Toast(this).apply {
            view = block(this@toast)
        }
        ToastConfig.toast?.show()
    }
}

fun FragmentActivity.toast(block: Toast.(Context) -> View) {
    applicationContext.toast(block)
}

fun Fragment.toast(config: Toast.(Context) -> View) {
    context?.toast(config)
}
//</editor-fold>

//<editor-fold desc="长吐司">
fun Context.longToast(msg: Int) {
    longToast(getString(msg))
}

fun FragmentActivity.longToast(msg: Int) {
    applicationContext.longToast(msg)
}

fun Fragment.longToast(msg: Int) {
    context?.longToast(msg)
}

fun Context.longToast(msg: CharSequence?) {
    showDefault(msg, false)
}

fun FragmentActivity.longToast(msg: CharSequence?) {
    applicationContext.longToast(msg)
}

fun Fragment.longToast(msg: CharSequence?) {
    context?.longToast(msg)
}
//</editor-fold>


/**
 * 显示常用默认的吐司或者全局设置的吐司样式
 *
 * @param msg 吐司内容
 * @param short 消息停留时间间隔
 */
@SuppressLint("ShowToast")
private fun Context?.showDefault(msg: CharSequence?, short: Boolean = true) {
    if (this == null || msg == null) return
    ToastConfig.toast?.cancel()
    runMain {
        ToastConfig.toast = if (ToastConfig.onToast != null) {
            Toast(this).apply {
                duration = if (short) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
                ToastConfig.onToast?.invoke(this, this@showDefault, msg)
                if (view == null) {
                    view = Toast.makeText(this@showDefault, msg, Toast.LENGTH_SHORT).view
                }
            }
        } else Toast.makeText(this, msg, Toast.LENGTH_SHORT)
        ToastConfig.toast?.show()
    }
}

/**
 * 跟随生命周期自动取消所有吐司
 *
 * @see Lifecycle.Event.ON_PAUSE 默认为不可见时自动取消吐司
 */
private fun LifecycleOwner.lifeToast(lifeEvent: Lifecycle.Event = Lifecycle.Event.ON_PAUSE) {
    lifecycle.addObserver(object : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (event == lifeEvent) {
                ToastConfig.cancel()
            }
        }
    })
}
