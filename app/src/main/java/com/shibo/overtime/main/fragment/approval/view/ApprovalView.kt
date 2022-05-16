package com.shibo.overtime.main.fragment.approval.view

import com.shibo.overtime.main.fragment.approval.model.entity.AgreeModelEntity
import com.shibo.overtime.main.fragment.approval.model.entity.ApprovalModelEntity

/**
 * @author shibo
 * @date 2022/5/15
 *
 */
interface ApprovalView {

    fun approvalSuccess(response: ApprovalModelEntity)

    fun approvalFailure(message: String)

    fun agreeSuccess(response: AgreeModelEntity)

    fun agreeFailure(message: String)
}