/*
 * MIT License
 *
 * Copyright (c) 2023 劉強東 https://github.com/liangjingkanji
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.drake.tooltip

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.widget.Toast
import com.drake.tooltip.interfaces.ToastFactory

@SuppressLint("StaticFieldLeak")
object ToastConfig {

    internal var toast: Toast? = null
    internal lateinit var context: Context

    /** 构建吐司 */
    @JvmField
    var toastFactory: ToastFactory = ToastFactory

    /**
     * 初始化
     * 如果应用存在多进程使用则必须使用本方法初始化, 否则是可选
     * @param toastFactory 构建吐司
     */
    @JvmOverloads
    @JvmStatic
    fun initialize(application: Application, toastFactory: ToastFactory? = null) {
        this.context = application
        if (toastFactory != null) {
            this.toastFactory = toastFactory
        }
    }

    /** 取消吐司显示 */
    @JvmStatic
    fun cancel() {
        toast?.cancel()
    }

}



