package com.drake.tooltip.sample

import android.app.Application
import android.content.Context
import android.view.View
import com.drake.tooltip.ToastConfig
import com.drake.tooltip.sample.fragment.TOAST_ERROR
import kotlinx.android.synthetic.main.layout_toast_error.view.*

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        ToastConfig.apply {

            // 全局Toast(不包含等级和自定义视图Toast)
            onToast { context: Context, msg: CharSequence ->
                view = View.inflate(context, R.layout.layout_toast_default, null)
                    .apply { tv_msg.text = msg }
            }

            // 等级Toast
            onLevel { context, msg, level ->
                when (level) {
                    TOAST_ERROR -> {
                        view = View.inflate(context, R.layout.layout_toast_error, null)
                            .apply { tv_msg.text = msg }
                    }
                }
            }
        }
    }
}