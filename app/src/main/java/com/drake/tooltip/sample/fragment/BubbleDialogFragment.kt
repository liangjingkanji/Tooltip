package com.drake.tooltip.sample.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.drake.tooltip.dialog.BubbleDialog
import com.drake.tooltip.sample.R
import kotlinx.android.synthetic.main.fragment_bubble_dialog.*
import kotlin.concurrent.thread

class BubbleDialogFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bubble_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tv_show.setOnClickListener {
            val bubbleDialog = BubbleDialog(requireContext())
            waitDismiss(bubbleDialog)
            bubbleDialog.show()
        }

        tv_thread.setOnClickListener {
            val bubbleDialog = BubbleDialog(requireContext())
            waitDismiss(bubbleDialog)
            thread { bubbleDialog.show() } // 开启一个子线程来显示吐司
        }

        tv_specify_title.setOnClickListener {
            val bubbleDialog = BubbleDialog(requireContext(), "清除缓存中")
            waitDismiss(bubbleDialog)
            bubbleDialog.show()
        }
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