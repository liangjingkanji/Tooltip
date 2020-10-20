package com.drake.tooltip

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast

object ToastConfig {

    internal var toast: Toast? = null
    internal var onLevel: (Toast.(context: Context, msg: CharSequence, level: Int) -> Unit)? = null
    internal var onToast: (Toast.(context: Context, msg: CharSequence) -> Unit)? = null

    fun cancel() {
        toast?.cancel()
    }

    /**
     * 全局等级吐司配置
     */
    fun onLevel(block: Toast.(context: Context, msg: CharSequence, level: Int) -> Unit) {
        onLevel = block
    }

    /**
     * 全局吐司的配置
     */
    fun onToast(block: Toast.(context: Context, msg: CharSequence) -> Unit) {
        onToast = block
    }

}

internal fun runMain(block: () -> Unit) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        block()
    } else {
        Handler(Looper.getMainLooper()).post { block() }
    }
}



