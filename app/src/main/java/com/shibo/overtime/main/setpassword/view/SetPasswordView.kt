package com.shibo.overtime.main.setpassword.view

import com.shibo.overtime.main.fragment.approval.model.entity.AgreeModelEntity

/**
 * @author shibo
 * @date 2022/5/15
 *
 */
interface SetPasswordView {

    fun setPasswordSuccess(response: AgreeModelEntity)

    fun setPasswordFailure(message: String)
}