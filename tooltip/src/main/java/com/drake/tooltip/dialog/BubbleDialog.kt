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

package com.drake.tooltip.dialog

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.RotateDrawable
import android.os.Bundle
import android.view.animation.LinearInterpolator
import androidx.annotation.StyleRes
import com.drake.tooltip.R
import com.drake.tooltip.runMain
import kotlinx.android.synthetic.main.layout_bubble_dialog.*

/**
 * iOS风格的加载对话框
 * @param title 加载对话框的标题
 */
class BubbleDialog @JvmOverloads constructor(
    context: Context,
    var title: String = context.getString(R.string.bubble_loading_title),
    @StyleRes themeResId: Int = R.style.BubbleDialog,
) : Dialog(context, themeResId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_bubble_dialog)
        tv_title.text = title
        val rotateDrawable = iv_loading.background as RotateDrawable
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
                tv_title.text = text
            }
        } else {
            title = text
        }
    }
}