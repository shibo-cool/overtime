package com.shibo.overtime.main.fragment.approval.presenter

import android.content.Context
import com.shibo.overtime.base.BaseModelListener
import com.shibo.overtime.base.BasePresenter
import com.shibo.overtime.main.fragment.approval.model.AgreeModel
import com.shibo.overtime.main.fragment.approval.model.ApprovalModel
import com.shibo.overtime.main.fragment.approval.model.entity.AgreeModelEntity
import com.shibo.overtime.main.fragment.approval.model.entity.ApprovalModelEntity
import com.shibo.overtime.main.fragment.approval.view.ApprovalView

/**
 * @author shibo
 * @date 2022/5/15
 *
 */
class ApprovalPresenter: BasePresenter {

    var mContext: Context? = null
    var mView: ApprovalView? = null

    constructor(context: Context, view: ApprovalView){
        mContext = context
        mView = view
    }

    /**
     * 获取审批记录列表
     */
    fun requestApproval(page: Int){
        ApprovalModel(mContext!!, page, object: BaseModelListener<ApprovalModelEntity> {
            override fun onSuccess(response: ApprovalModelEntity) {
                if(isSuccess(response)) {
                    mView?.approvalSuccess(response)
                } else {
                    mView?.approvalFailure(response.info?:"接口返回错误")
                }
            }

            override fun onFailure(message: String) {

                mView?.approvalFailure(message)
            }

        }).start()
    }

    /**
     * 点击同意或拒绝
     */
    fun requestAgree(agree: String, ids: String){
        AgreeModel(mContext!!, agree, ids, object: BaseModelListener<AgreeModelEntity>{
            override fun onSuccess(response: AgreeModelEntity) {
                if(isSuccess(response)) {
                    mView?.agreeSuccess(response)
                } else {
                    mView?.agreeFailure(response.info?:"接口返回错误")
                }
            }

            override fun onFailure(message: String) {
                mView?.agreeFailure(message)
            }

        }).start()
    }
}