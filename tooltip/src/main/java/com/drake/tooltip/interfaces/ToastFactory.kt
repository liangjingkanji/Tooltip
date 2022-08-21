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

package com.drake.tooltip.interfaces

import android.content.Context
import android.widget.Toast
import com.drake.tooltip.ToastConfig

interface ToastFactory {

    companion object DEFAULT : ToastFactory {
        override fun onCreate(
            context: Context,
            message: CharSequence,
            duration: Int,
            tag: Any?
        ): Toast? {
            return Toast.makeText(ToastConfig.context, message, duration)
        }
    }

    /**
     * 创建吐司
     * @param context Application
     * @param message 吐司内容
     * @param tag 吐司标签
     */
    fun onCreate(
        context: Context,
        message: CharSequence,
        duration: Int,
        tag: Any? = null
    ): Toast?
}