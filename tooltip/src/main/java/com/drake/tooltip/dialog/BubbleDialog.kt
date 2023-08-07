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

package com.drake.tooltip.dialog

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.RotateDrawable
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StyleRes
import com.drake.tooltip.R
import com.drake.tooltip.internal.ThreadUtils.runMain

/**
 * iOS风格的加载对话框
 * @param title 加载对话框的标题
 */
class BubbleDialog @JvmOverloads constructor(
    context: Context,
    private var title: String = context.getString(R.string.bubble_loading_title),
    @StyleRes themeResId: Int = R.style.BubbleDialog,
) : Dialog(context, themeResId) {

    private var tvTitle: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_bubble_dialog)
        tvTitle = findViewById(R.id.tv_title)
        tvTitle?.text = title
        val rotateDrawable = findViewById<ImageView>(R.id.iv_loading).background as RotateDrawable
        ObjectAnimator.ofInt(rotateDrawable, "level", 0, 10000).apply {
            duration = 2000
            repeatCount = ValueAnimator.INFINITE
            interpolator = LinearInterpolator()
            start()
        }
    }

    override fun show() {
        runMain {
            super.show()
        }
    }

    /**
     * 更新标题文本
     */
    fun updateTitle(text: String) {
        if (isShowing) {
            runMain {
                tvTitle?.text = text
            }
        } else {
            title = text
        }
    }
}