package com.shibo.overtime.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.shibo.overtime.R

/**
 * @author shibo
 * @date 2022/5/15
 *
 */
class SexDialog: DialogFragment(), View.OnClickListener {

    private var mView: View? = null


    private var mTvSexMale: TextView? = null
    private var mTvSexFemale: TextView? = null
    private var mTvCancel: TextView? = null

    private var mListener: Listener? = null


    fun setListener(listener: Listener?) {
        mListener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.fragment_dialog_style)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = LayoutInflater.from(activity).inflate(R.layout.dialog_sex, null)
        initView()
        setListener()
        return mView
    }

    fun initView() {
        mTvSexMale = mView!!.findViewById(R.id.tv_sex_male)
        mTvSexFemale = mView!!.findViewById(R.id.tv_sex_female)
        mTvCancel = mView!!.findViewById(R.id.tv_cancel)
    }

    fun setListener() {
        mTvSexMale?.setOnClickListener(this)
        mTvSexFemale?.setOnClickListener(this)
        mTvCancel?.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        dismiss()
        if (mListener == null) return
        when (v) {
            mTvSexMale -> mListener?.sexMale()
            mTvSexFemale -> mListener?.sexFemale()
            mTvCancel -> mListener?.cancel()
        }
    }

    interface Listener {
        fun sexMale()
        fun sexFemale()
        fun cancel()
    }
}