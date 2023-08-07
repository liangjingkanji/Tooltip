package com.drake.tooltip.sample.base

import android.app.Application
import com.drake.tooltip.ToastConfig

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ToastConfig.initialize(this)
        // ToastConfig.initialize(this, ToastGravityFactory()) // 屏幕居中显示吐司
    }
}