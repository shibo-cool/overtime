package com.shibo.overtime.main.approvalNote.presenter

import android.content.Context
import com.shibo.overtime.base.BaseModelListener
import com.shibo.overtime.base.BasePresenter
import com.shibo.overtime.main.approvalNote.view.ApprovalNoteView
import com.shibo.overtime.main.fragment.approval.model.ApprovalModel
import com.shibo.overtime.main.fragment.approval.model.entity.ApprovalModelEntity

/**
 * @author shibo
 * @date 2022/5/15
 *
 */
class ApprovalNotePresenter: BasePresenter {

    private var mContext: Context? = null
    private var mView: ApprovalNoteView? = null

    constructor(context: Context, view: ApprovalNoteView){
        mContext = context
        mView = view
    }

    fun requestApproval(page: Int){
        ApprovalModel(mContext!!, page, object: BaseModelListener<ApprovalModelEntity>{
            override fun onSuccess(response: ApprovalModelEntity) {
                if(isSuccess(response)) {
                    mView?.approvalNoteSuccess(response)
                } else {
                    mView?.approvalNoteFailure(response.info?:"接口返回错误")
                }
            }

            override fun onFailure(message: String) {
                mView?.approvalNoteFailure(message)
            }

        })
    }
}