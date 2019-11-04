package com.drake.tooltip.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.drake.tooltip.longToast
import com.drake.tooltip.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_short.setOnClickListener { toast("当前时间 = ${System.currentTimeMillis()}") }
        btn_long.setOnClickListener { longToast("当前时间 = ${System.currentTimeMillis()}") }
    }
}
