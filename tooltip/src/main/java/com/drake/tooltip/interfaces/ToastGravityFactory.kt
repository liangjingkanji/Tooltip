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
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import com.drake.tooltip.R

/**
 * 屏幕居中显示吐司
 * @param gravity 对齐方式
 * @param layout 吐司布局, 要求消息内容TextView的ID使用[@android:id/message]
 * @param xOffset x轴偏移量
 * @param yOffset y轴偏移量
 */
open class ToastGravityFactory @JvmOverloads constructor(
    val gravity: Int = Gravity.CENTER,
    @LayoutRes val layout: Int = R.layout.layout_toast_gravity,
    val xOffset: Int = 0,
    val yOffset: Int = 0,
) : ToastFactory {

    /**
     * 创建吐司
     * @param context Application
     * @param message 吐司内容
     * @param tag 吐司标签
     */
    override fun onCreate(
        context: Context,
        message: CharSequence,
        duration: Int,
        tag: Any?
    ): Toast? {
        val toast = Toast.makeText(context, message, duration)
        val view = View.inflate(context, layout, null)
        view.findViewById<TextView>(android.R.id.message).text = message
        toast.view = view
        toast.setGravity(gravity, xOffset, yOffset)
        return toast
    }
}