package com.drake.tooltip

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner


//<editor-fold desc="短吐司">
fun toast(msg: Int) {
    showDefault(ToastConfig.app.getString(msg))
}

fun toast(msg: CharSequence?) {
    showDefault(msg)
}

//</editor-fold>

//<editor-fold desc="等级吐司">
/**
 * 在函数[ToastConfig.onToast]通过参数[level]来判断返回不同的View
 */
@SuppressLint("ShowToast")
fun toast(msg: CharSequence?, level: Int) {
    msg ?: return
    ToastConfig.toast?.cancel()
    runMain {
        val toast = Toast(ToastConfig.app).apply {
            ToastConfig.onLevel?.invoke(this, ToastConfig.app, msg, level)
            if (view == null) {
                view = Toast.makeText(ToastConfig.app, msg, Toast.LENGTH_SHORT).view
            }
        }
        ToastConfig.toast = toast
        ToastConfig.toast?.show()
    }
}
//</editor-fold>

//<editor-fold desc="自定义视图">
/**
 * 函数参数要求返回一个视图
 */
fun toast(block: Toast.(Context) -> View) {
    ToastConfig.toast?.cancel()
    runMain {
        ToastConfig.toast = Toast(ToastConfig.app).apply {
            view = block(ToastConfig.app)
        }
        ToastConfig.toast?.show()
    }
}
//</editor-fold>

//<editor-fold desc="长吐司">
fun longToast(msg: Int) {
    longToast(ToastConfig.app.getString(msg))
}

fun longToast(msg: CharSequence?) {
    showDefault(msg, false)
}
//</editor-fold>


/**
 * 显示常用默认的吐司或者全局设置的吐司样式
 *
 * @param msg 吐司内容
 * @param short 消息停留时间间隔
 */
@SuppressLint("ShowToast")
private fun showDefault(msg: CharSequence?, short: Boolean = true) {
    if (msg == null) return
    ToastConfig.toast?.cancel()
    runMain {
        ToastConfig.toast = if (ToastConfig.onToast != null) {
            Toast(ToastConfig.app).apply {
                duration = if (short) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
                ToastConfig.onToast?.invoke(this, ToastConfig.app, msg)
                if (view == null) {
                    view = Toast.makeText(ToastConfig.app, msg, Toast.LENGTH_SHORT).view
                }
            }
        } else Toast.makeText(ToastConfig.app, msg, Toast.LENGTH_SHORT)
        ToastConfig.toast?.show()
    }
}