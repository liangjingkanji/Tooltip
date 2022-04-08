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


/**
 * 短时间显示的吐司
 * @param msg 吐司内容
 * @param tag 标记
 */
fun toast(msg: Int, tag: Any? = null) {
    showToast(ToastConfig.application.getString(msg), 0, tag)
}

/**
 * 短时间显示的吐司
 * @param msg 吐司内容
 * @param tag 标记
 */
fun toast(msg: CharSequence?, tag: Any? = null) {
    showToast(msg, 0, tag)
}

/**
 * 长时间显示的吐司
 * @param msg 吐司内容
 * @param tag 标记
 */
fun longToast(msg: Int, tag: Any? = null) {
    longToast(ToastConfig.application.getString(msg), tag)
}

/**
 * 长时间显示的吐司
 * @param msg 吐司内容
 * @param tag 标记
 */
fun longToast(msg: CharSequence?, tag: Any? = null) {
    showToast(msg, 1, tag)
}

/**
 * 显示吐司
 * @param msg 吐司内容
 * @param duration 吐司显示时长 0 短时间显示 1 长时间显示
 * @param tag 标记
 */
@SuppressLint("ShowToast")
private fun showToast(msg: CharSequence?, duration: Int, tag: Any?) {
    msg ?: return
    ToastConfig.toast?.cancel()
    runMain {
        ToastConfig.toast = ToastConfig.toastFactory.onCreate(ToastConfig.application, msg, duration, tag)
        ToastConfig.toast?.show()
    }
}