package com.drake.tooltip.sample

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.drake.tooltip.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toast_error.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // 短吐司
        btn_short.setOnClickListener {
            // 开启子线程显示吐司
            Thread(Runnable { toast("开启子线程显示吐司开启子线程显示吐司开启子线程显示吐司开启子线程显示吐司开启子线程显示吐司开启子线程显示吐司开启子线程显示吐司 当前时间 = ${System.currentTimeMillis()}") }).start()
        }

        // 长吐司
        btn_long.setOnClickListener {
            //            longToast("当前时间 = ${System.currentTimeMillis()}")

            toast {
                view = View.inflate(it, R.layout.layout_toast_error, null)
                    .apply { tv_msg.text = "吴彦祖" }

                Log.d("日志", "(MainActivity.kt:32)    result = ${duration}")
            }
        }

        // 自定义视图吐司
        btn_custom.setOnClickListener {
            toast {
                setGravity(Gravity.CENTER, 0, 0)
                ProgressBar(this@MainActivity)
            }
        }

        // 自定义全局等级吐司
        btn_level_toast.setOnClickListener {
            toast("这是一条很长的错误消息", App.LEVEL_ERROR)
        }

    }

}
