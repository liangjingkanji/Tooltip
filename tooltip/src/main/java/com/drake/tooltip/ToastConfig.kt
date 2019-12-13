package com.drake.tooltip

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast

object ToastConfig {

    internal var toast: Toast? = null
    internal var onLevel: (Toast.(context: Context, msg: CharSequence, level: Int) -> View?)? = null
    internal var onToast: (Toast.(context: Context, msg: CharSequence) -> View)? = null

    /**
     * 全局等级吐司配置
     */
    fun onLevel(block: Toast.(context: Context, msg: CharSequence, level: Int) -> View?) {
        onLevel = block
    }


    /**
     * 全局吐司的配置
     */
    fun onToast(block: Toast.(context: Context, msg: CharSequence) -> View) {
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



