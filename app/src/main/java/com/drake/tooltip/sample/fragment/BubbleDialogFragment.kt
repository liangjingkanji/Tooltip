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

package com.drake.tooltip.sample.fragment

import android.app.Dialog
import androidx.lifecycle.lifecycleScope
import com.drake.engine.base.EngineFragment
import com.drake.tooltip.dialog.BubbleDialog
import com.drake.tooltip.sample.R
import com.drake.tooltip.sample.databinding.FragmentBubbleDialogBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class BubbleDialogFragment : EngineFragment<FragmentBubbleDialogBinding>(R.layout.fragment_bubble_dialog) {


    override fun initView() {
        binding.tvShow.setOnClickListener {
            val bubbleDialog = BubbleDialog(requireContext())
            waitDismiss(bubbleDialog)
            bubbleDialog.show()
        }

        binding.tvThread.setOnClickListener {
            val bubbleDialog = BubbleDialog(requireContext())
            waitDismiss(bubbleDialog)
            thread { bubbleDialog.show() } // 开启一个子线程来显示吐司
        }

        binding.tvSpecifyTitle.setOnClickListener {
            val bubbleDialog = BubbleDialog(requireContext(), "清除缓存中")
            waitDismiss(bubbleDialog)
            bubbleDialog.show()
        }

        binding.tvUpdateTitle.setOnClickListener {
            val bubbleDialog = BubbleDialog(requireContext(), "标题0")
            waitDismiss(bubbleDialog)
            lifecycleScope.launch(Dispatchers.IO) {
                bubbleDialog.updateTitle("标题1")
                bubbleDialog.show()
                delay(500L)
                bubbleDialog.updateTitle("标题2")
                delay(500L)
                bubbleDialog.updateTitle("标题3")
            }
        }
    }

    override fun initData() {
    }

    /**
     * 等待2秒关闭对话框
     */
    private fun waitDismiss(dialog: Dialog) {
        view?.postDelayed({
            dialog.dismiss()
        }, 1500)
    }
}