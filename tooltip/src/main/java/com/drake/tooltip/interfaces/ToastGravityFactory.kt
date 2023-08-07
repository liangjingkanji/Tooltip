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