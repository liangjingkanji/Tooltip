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
import androidx.annotation.StringRes
import com.drake.tooltip.internal.ThreadUtils.runMain


/**
 * 短时间显示的吐司
 * @param msg 吐司内容
 * @param tag 标记, 标记用于[com.drake.tooltip.interfaces.ToastFactory]区分吐司
 */
@JvmOverloads
fun toast(@StringRes msg: Int, tag: Any? = null) {
    showToast(ToastConfig.context.getString(msg), 0, tag)
}

/**
 * 短时间显示的吐司
 * @param msg 吐司内容
 * @param tag 标记, 标记用于[com.drake.tooltip.interfaces.ToastFactory]区分吐司
 */
@JvmOverloads
fun toast(msg: CharSequence?, tag: Any? = null) {
    showToast(msg, 0, tag)
}

/**
 * 长时间显示的吐司
 * @param msg 吐司内容
 * @param tag 标记, 标记用于[com.drake.tooltip.interfaces.ToastFactory]区分吐司
 */
@JvmOverloads
fun longToast(@StringRes msg: Int, tag: Any? = null) {
    longToast(ToastConfig.context.getString(msg), tag)
}

/**
 * 长时间显示的吐司
 * @param msg 吐司内容
 * @param tag 标记, 标记用于[com.drake.tooltip.interfaces.ToastFactory]区分吐司
 */
@JvmOverloads
fun longToast(msg: CharSequence?, tag: Any? = null) {
    showToast(msg, 1, tag)
}

/**
 * 显示吐司
 * @param msg 吐司内容
 * @param duration 吐司显示时长 0 短时间显示 1 长时间显示
 * @param tag 标记, 标记用于[com.drake.tooltip.interfaces.ToastFactory]区分吐司
 */
@SuppressLint("ShowToast")
private fun showToast(msg: CharSequence?, duration: Int, tag: Any?) {
    msg ?: return
    ToastConfig.toast?.cancel()
    runMain {
        ToastConfig.toast = ToastConfig.toastFactory.onCreate(ToastConfig.context, msg, duration, tag)
        ToastConfig.toast?.show()
    }
}