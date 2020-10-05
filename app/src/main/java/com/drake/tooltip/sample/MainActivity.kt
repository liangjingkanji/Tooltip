package com.drake.tooltip.sample

import android.os.Bundle
import android.view.Gravity
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.drake.tooltip.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 短吐司
        btn_short.setOnClickListener {
            toast("吴彦祖")
        }

        // 长吐司
        btn_long.setOnClickListener {
            // 开启子线程显示吐司
            Thread(Runnable { toast("开启子线程显示吐司开启子线程显示吐司开启子线程显示吐司开启子线程显示吐司开启子线程显示吐司开启子线程显示吐司开启子线程显示吐司 当前时间 = ${System.currentTimeMillis()}") }).start()
        }

        // 自定义全局等级吐司
        btn_level_toast.setOnClickListener {
            toast("这是一条很长的错误消息", App.LEVEL_ERROR)
        }

        // 自定义视图吐司
        btn_custom.setOnClickListener {
            toast {
                setGravity(Gravity.CENTER, 0, 0)
                ProgressBar(this@MainActivity)
            }
        }
    }

}
