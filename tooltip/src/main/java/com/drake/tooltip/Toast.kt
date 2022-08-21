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