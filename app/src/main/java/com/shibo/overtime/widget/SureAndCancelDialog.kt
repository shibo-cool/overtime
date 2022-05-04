package com.shibo.overtime.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.shibo.overtime.R

class SureAndCancelDialog: DialogFragment {

    private var mTitle = ""
    private var mMessage = ""
    private var mContext: Context? = null
    private var mListener: DialogListener? = null

    constructor(context: Context, title: String, message: String, listener: DialogListener): super(){
        mTitle = title
        mMessage = message
        mContext = context
        mListener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(mContext!!)
        dialog.setContentView(R.layout.dialog_sure_and_cancle)
        val btOk = dialog.findViewById<View>(R.id.btn_sure) as Button
        btOk.setOnClickListener {
            mListener?.ok()
            dismiss()
        }
        val btCancel = dialog.findViewById<View>(R.id.btn_cancel)
        btCancel.setOnClickListener {
            dismiss()
        }
        val tvTitle = dialog.findViewById<TextView>(R.id.tv_title)
        if(!TextUtils.isEmpty(mTitle)){
            tvTitle.text = mTitle
            tvTitle.visibility = View.VISIBLE
        } else tvTitle.visibility = View.GONE
        val tvContent = dialog.findViewById<View>(R.id.tv_msg) as TextView
        tvContent.text = mMessage
        return dialog
    }

    override fun onStart() {
        super.onStart()
        if (dialog != null && dialog!!.window != null) {
            //某些手机受主题影响，导致dialog默认带padding，导致不能全屏，故需要去掉padding（oppo android11 会有问题）
            dialog!!.window!!.decorView.setPadding(0, 0, 0, 0)
        }
    }

    interface DialogListener{
        fun ok()
    }
}