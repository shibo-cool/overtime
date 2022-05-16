package com.shibo.overtime.main.fragment.approval

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shibo.overtime.R
import com.shibo.overtime.base.BaseFragment
import com.shibo.overtime.main.approvalNote.ApprovalNoteActivity
import com.shibo.overtime.main.fragment.approval.adapter.ApprovalAdapter
import com.shibo.overtime.main.fragment.approval.model.entity.AgreeModelEntity
import com.shibo.overtime.main.fragment.approval.model.entity.ApprovalModelEntity
import com.shibo.overtime.main.fragment.approval.presenter.ApprovalPresenter
import com.shibo.overtime.main.fragment.approval.view.ApprovalView
import com.shibo.overtime.widget.MyToast

class ApprovalFragment: BaseFragment(), ApprovalView {

    /**
     * 审批记录按钮
     */
    private var mTvApproval: TextView? = null

    /**
     * 审批项列表
     */
    private var mRcv: RecyclerView? = null

    /**
     * 全选按钮
     */
    private var mRbAll: CheckBox? = null

    /**
     * 拒绝按钮
     */
    private var mBtn1: Button? = null

    /**
     * 同意按钮
     */
    private var mBtn2: Button? = null

    /**
     * 没有审批记录界面
     */
    private var mRltNoData: RelativeLayout? = null

    private var mPresenter: ApprovalPresenter? = null
    private var mAdapter: ApprovalAdapter? = null

    /**
     * 页码
     */
    private var mPage: Int = 0

    companion object{
        /**
         * 同意加班请求
         */
        const val agree = "2"

        /**
         * 拒绝加班请求
         */
        const val refuse = "3"
    }

    override fun getContentView(): Int {
        return R.layout.fragment_approval
    }

    override fun initView(view: View) {
        mTvApproval = view.findViewById(R.id.tv_approval_notes)
        mRcv = view.findViewById(R.id.rcv)
        mRbAll = view.findViewById(R.id.rb_all)
        mBtn1 = view.findViewById(R.id.btn1)
        mBtn2 = view.findViewById(R.id.btn2)
        mRltNoData = view.findViewById(R.id.rltNoData)
    }

    override fun initData() {
        mPresenter = ApprovalPresenter(activity as Context, this)
        mAdapter = ApprovalAdapter(activity as Context)
        mAdapter?.setChange(object: ApprovalAdapter.ApprovalAdapterChangeListener{
            override fun checkedChangeAll(param: Boolean) {
                mRbAll?.setOnCheckedChangeListener(null)
                mRbAll?.isChecked = param
                mRbAll?.setOnCheckedChangeListener(checkAll)
            }
        })
        mRcv?.adapter = mAdapter
        mRcv?.layoutManager = LinearLayoutManager(activity as Context)
        mRltNoData?.visibility = View.VISIBLE
        mPresenter?.requestApproval(mPage)
    }

    override fun setListener() {
        mBtn1?.setOnClickListener {
            approval(refuse, mAdapter?.getSelectIds()?:"")
        }

        mBtn2?.setOnClickListener {
            approval(agree, mAdapter?.getSelectIds()?:"")
        }

        mRbAll?.setOnCheckedChangeListener(checkAll)

        mTvApproval?.setOnClickListener {
            ApprovalNoteActivity.start(activity as Context)
        }
    }

    /**
     * 点击全选check
     */
    val checkAll =  object: CompoundButton.OnCheckedChangeListener{
        override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
            mAdapter?.setCheckAll(p1)
        }
    }

    /**
     * 点击同意或拒绝
     */
    private fun approval(agree: String, ids: String){
        if(TextUtils.isEmpty(ids)){
            MyToast.showToast(activity as Context, "请选择加班项")
            return
        }
        mPresenter?.requestAgree(agree, ids)
    }

    override fun approvalSuccess(response: ApprovalModelEntity) {
        if(mPage == 0) {
            if (response.data?.approveList != null && response.data?.approveList?.size != 0) {
                mAdapter?.setData(response.data?.approveList)
                mRltNoData?.visibility = View.GONE
                if ("1".equals(response.data?.hasNext, false)) {
                    // todo 若还有下一页则设置可上拉加载
                }
            } else {
                mRltNoData?.visibility = View.VISIBLE
            }
        } else {
            if (response.data?.approveList != null && response.data?.approveList?.size != 0) {
                val i = mAdapter?.itemCount
                mAdapter?.addData(response.data?.approveList)
                mRcv?.scrollToPosition(i?:0)
                if ("1".equals(response.data?.hasNext, false)) {
                    // todo 若还有下一页则设置可上拉加载
                }
            }
        }
    }

    override fun approvalFailure(message: String) {
        mRltNoData?.visibility = View.VISIBLE
    }

    override fun agreeSuccess(response: AgreeModelEntity) {
        mPage = 0
        mPresenter?.requestApproval(mPage)
    }

    override fun agreeFailure(message: String) {
        MyToast.showToast(activity as Context, message)
    }
}