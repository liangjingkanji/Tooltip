package com.drake.tooltip.sample.ui.fragment

import com.drake.engine.base.EngineFragment
import com.drake.tooltip.ToastConfig
import com.drake.tooltip.interfaces.ToastFactory
import com.drake.tooltip.interfaces.ToastGravityFactory
import com.drake.tooltip.longToast
import com.drake.tooltip.sample.R
import com.drake.tooltip.sample.databinding.FragmentToastBinding
import com.drake.tooltip.toast

class ToastFragment : EngineFragment<FragmentToastBinding>(R.layout.fragment_toast) {

    override fun initView() {
        binding.tvShort.setOnClickListener {
            toast("短时间提示")
        }

        binding.tvLong.setOnClickListener {
            longToast("长时间提示")
        }

        binding.tvConfig.setOnClickListener {
            ToastConfig.toastFactory = ToastGravityFactory() // 正常情况下你应当在Application中初始化配置
            toast("居中屏幕显示推送")
            ToastConfig.toastFactory = ToastFactory // 重置为默认吐司
        }
    }

    override fun initData() {
    }
}