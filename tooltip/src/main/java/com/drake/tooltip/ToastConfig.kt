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

package com.drake.tooltip

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import com.drake.tooltip.interfaces.ToastFactory

@SuppressLint("StaticFieldLeak")
object ToastConfig {

    internal var toast: Toast? = null
    internal lateinit var application: Application

    /** 构建吐司 */
    var toastFactory: ToastFactory = ToastFactory

    /**
     * 初始化
     * 如果应用存在多进程使用则必须使用本方法初始化, 否则是可选
     * @param toastFactory 构建吐司
     */
    fun initialize(application: Application, toastFactory: ToastFactory? = null) {
        this.application = application
        if (toastFactory != null) {
            this.toastFactory = toastFactory
        }
    }

    /** 取消吐司显示 */
    fun cancel() {
        toast?.cancel()
    }

}



