package com.drake.tooltip.sample.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.drake.tooltip.longToast
import com.drake.tooltip.sample.R
import com.drake.tooltip.toast
import kotlinx.android.synthetic.main.fragment_toast.*

const val TOAST_ERROR = 0

class ToastFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_toast, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tv_short.setOnClickListener {
            toast("短时间提示")
        }

        tv_long.setOnClickListener {
            longToast("提示")
        }

        tv_config.setOnClickListener {
            toast {
                setGravity(Gravity.CENTER, 0, 0)
                ProgressBar(context) // 自定义视图显示加载进度
            }
        }

        tv_level.setOnClickListener {
            toast("这是一个自定义等级为错误的提示", TOAST_ERROR) // 请查看[App]中的ToastConfig.onLevel回调进行定制
        }
    }
}