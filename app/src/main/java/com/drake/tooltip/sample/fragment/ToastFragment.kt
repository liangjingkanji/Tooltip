/*
 * Copyright (C) 2018 Drake, https://github.com/liangjingkanji
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.drake.tooltip.sample.fragment

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