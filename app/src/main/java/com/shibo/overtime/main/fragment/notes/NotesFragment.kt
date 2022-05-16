package com.shibo.overtime.main.fragment.notes

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shibo.overtime.R
import com.shibo.overtime.base.BaseFragment
import com.shibo.overtime.main.fragment.notes.adapter.NotesAdapter
import com.shibo.overtime.main.fragment.notes.model.entity.NotesModelEntity
import com.shibo.overtime.main.fragment.notes.presenter.NotesPresenter
import com.shibo.overtime.main.fragment.notes.view.NotesView

/**
 * @author shibo
 * @date 2022/5/10
 * 加班记录页
 */
class NotesFragment: BaseFragment(), NotesView {

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
     * 下拉刷新控件
     */
    private var mSrlRefresh: SwipeRefreshLayout? = null

    /**
     * 加班记录列表
     */
    private var mRvNotes: RecyclerView? = null

    private var mPresenter: NotesPresenter? = null
    private var mAdapter: NotesAdapter? = null

    /**
     * 当前页码
     */
    private var mPage: Int = 0

    override fun getContentView(): Int {
        return R.layout.fragment_notes
    }

    override fun initView(view: View) {
        mTvHour = view.findViewById(R.id.tv_hour)
        mTvMin = view.findViewById(R.id.tv_min)
        mTvClockAllTime = view.findViewById(R.id.tv_jb_time)
        mTvRestAllTime = view.findViewById(R.id.tv_tx_time)
        mSrlRefresh = view.findViewById(R.id.srl_refresh)
        mRvNotes = view.findViewById(R.id.rv_notes)
    }

    override fun initData() {
        mAdapter = NotesAdapter(activity as Context)
        mRvNotes?.layoutManager = LinearLayoutManager(activity as Context)
        mRvNotes?.adapter = mAdapter
        mPresenter = NotesPresenter(activity as Context, this)
        mPresenter?.requestNotes(mPage, mAdapter)
    }

    override fun setListener() {

    }

    /**
     * 加班记录接口请求成功回调
     */
    override fun notesSuccess(response: NotesModelEntity, canLoad: Int, isFirst: Boolean) {
        // todo canLoad返回的是是否有下一页,用于控制是否可以上拉加载
        if(isFirst) {
            // 第一页
            mTvHour?.text = response.data?.total?.remainTime?.hour
            mTvMin?.text = response.data?.total?.remainTime?.minute
            mTvClockAllTime?.text = response.data?.total?.recordTime
            mTvRestAllTime?.text = response.data?.total?.paidLeavelTime
        }
    }

    /**
     * 加班记录接口请求失败回调
     */
    override fun notesFailure(message: String) {

    }
}