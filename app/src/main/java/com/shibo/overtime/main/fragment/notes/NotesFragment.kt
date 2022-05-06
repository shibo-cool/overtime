package com.shibo.overtime.main.fragment.notes

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shibo.overtime.R
import com.shibo.overtime.base.BaseFragment

class NotesFragment: BaseFragment() {

    /**
     * 剩余时间（时）
     */
    private var mTvHour: TextView? = null

    /**
     * 剩余时间（分）
     */
    private var mTvMin: TextView? = null

    /**
     * 加班总时间
     */
    private var mTvClockAllTime: TextView? = null

    /**
     * 调休总时间
     */
    private var mTvRestAllTime: TextView? = null

    /**
     * 加班记录列表
     */
    private var mRvNotes: RecyclerView? = null

    override fun getContentView(): Int {
        return R.layout.fragment_notes
    }

    override fun initView(view: View) {
        mTvHour = view.findViewById(R.id.tv_hour)
        mTvMin = view.findViewById(R.id.tv_min)
        mTvClockAllTime = view.findViewById(R.id.tv_jb_time)
        mTvRestAllTime = view.findViewById(R.id.tv_tx_time)
        mRvNotes = view.findViewById(R.id.rv_notes)
    }

    override fun initData() {

    }

    override fun setListener() {

    }
}