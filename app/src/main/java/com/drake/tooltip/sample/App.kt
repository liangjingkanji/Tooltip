package com.drake.tooltip.sample

import android.app.Application
import android.content.Context
import android.view.View
import com.drake.tooltip.ToastConfig
import kotlinx.android.synthetic.main.layout_toast_error.view.*

class App : Application() {

    companion object {
        const val LEVEL_ERROR = 1
    }

    override fun onCreate() {
        super.onCreate()


        ToastConfig.apply {

            autoCancel = true

            onToast { context: Context, msg: CharSequence ->
                view =
                    View.inflate(context, R.layout.layout_toast_error, null)
                        .apply { tv_msg.text = msg }
            }

            onLevel { context, msg, level ->
                when (level) {
                    LEVEL_ERROR -> {
                        view = View.inflate(context, R.layout.layout_toast_error, null)
                            .apply { tv_msg.text = msg }
                    }
                }
            }
        }

    }
}