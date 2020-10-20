package com.drake.tooltip.dialog

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.RotateDrawable
import android.os.Bundle
import android.view.animation.LinearInterpolator
import com.drake.tooltip.R
import com.drake.tooltip.runMain
import kotlinx.android.synthetic.main.layout_bubble_dialog.*

/**
 * iOS风格的加载对话框
 * @param title 加载对话框的标题
 */
class BubbleDialog(
    context: Context,
    var title: String = context.getString(R.string.bubble_loading_title)
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
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
}