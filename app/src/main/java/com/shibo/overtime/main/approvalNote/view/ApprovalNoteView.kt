package com.shibo.overtime.main.approvalNote.view

import com.shibo.overtime.main.fragment.approval.model.entity.ApprovalModelEntity

/**
 * @author shibo
 * @date 2022/5/15
 *
 */
interface ApprovalNoteView {

    fun approvalNoteSuccess(response: ApprovalModelEntity)
    fun approvalNoteFailure(message: String)
}