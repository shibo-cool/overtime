package com.shibo.overtime.main.approvalNote

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shibo.overtime.R
import com.shibo.overtime.base.BaseActivity
import com.shibo.overtime.main.approvalNote.adapter.ApprovalNoteAdapter
import com.shibo.overtime.main.approvalNote.presenter.ApprovalNotePresenter
import com.shibo.overtime.main.approvalNote.view.ApprovalNoteView
import com.shibo.overtime.main.fragment.approval.adapter.ApprovalAdapter
import com.shibo.overtime.main.fragment.approval.model.entity.ApprovalModelEntity

/**
 * @author shibo
 * @date 2022/5/15
 * 审批记录页面
 */
class ApprovalNoteActivity: BaseActivity(), ApprovalNoteView {

    companion object{

        @JvmStatic
        fun start(context: Context){
            val intent = Intent(context, ApprovalNoteActivity::class.java)
            context.startActivity(intent)
        }
    }

    /**
     * 返回键
     */
    private var mBack: ImageView? = null

    /**
     * 已同意或已拒绝选择框
     */
    private var mRgTitle: RadioGroup? = null

    /**
     * 已同意
     */
    private var mAgree: RadioButton? = null

    /**
     * 已拒绝
     */
    private var mRefuse: RadioButton? = null

    /**
     * 列表
     */
    private var mRcv: RecyclerView? = null

    /**
     * 无审批记录页
     */
    private var mRltNoData: RelativeLayout? = null

    /**
     * 页码
     */
    private var mPage: Int = 0
    private var mPresenter: ApprovalNotePresenter? = null
    private var mAdapter: ApprovalNoteAdapter? = null

    override fun getContentView(): Int {
        return R.layout.activity_approval_note
    }

    override fun initView() {
        mBack = findViewById(R.id.back)
        mRgTitle = findViewById(R.id.rg_title)
        mAgree = findViewById(R.id.rbAgree)
        mRefuse = findViewById(R.id.rbRefuse)
        mRcv = findViewById(R.id.rcv)
        mRltNoData = findViewById(R.id.rltNoData)
    }

    override fun initData() {
        mPresenter = ApprovalNotePresenter(this, this)
        mAdapter = ApprovalNoteAdapter(this@ApprovalNoteActivity)
        mRcv?.adapter = mAdapter
        mRcv?.layoutManager = LinearLayoutManager(this@ApprovalNoteActivity)
        showLoading(false)
    }

    override fun setListener() {
        mBack?.setOnClickListener{
            finish()
        }
        mRgTitle?.setOnCheckedChangeListener(object: RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                if(p1 == R.id.rbAgree){
                    draw(mAgree, R.drawable.bgline)
                    draw(mRefuse, 0)
                    loadData()
                } else {
                    draw(mAgree, 0)
                    draw(mRefuse, R.drawable.bgline)
                    loadData()
                }
            }

        })
    }

    fun loadData(){
        mPage = 0
        showLoading(true)
        mPresenter?.requestApproval(mPage)
    }

    /**
     * 切换
     */
    fun draw(rb: RadioButton?, id: Int){
        if (id == 0) {
            rb?.setCompoundDrawables(null, null, null, null)
            return
        }
        val localDrawable = resources.getDrawable(id)
        localDrawable.setBounds(0, 0, localDrawable.minimumWidth, localDrawable.minimumHeight)
        rb?.setCompoundDrawables(null, null, null, localDrawable)
    }

    override fun approvalNoteSuccess(response: ApprovalModelEntity) {
        showLoading(false)
        mRltNoData?.visibility = View.GONE
        if(response.data != null && response.data?.approveList?.size != 0) {
            // 通过("1".equals(response.data?.hasNext,false))字段判断是否能加载下一页
            mAdapter?.setData(response.data?.approveList)
        } else {
            // 接口返回列表为空，设置不可上拉加载
        }
    }

    override fun approvalNoteFailure(message: String) {
        showLoading(false)
        mRltNoData?.visibility = View.VISIBLE
    }
}