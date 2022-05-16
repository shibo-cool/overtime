package com.shibo.overtime.main.fragment.my.view

import com.shibo.overtime.login.model.entity.LoginEntity

/**
 * @author shibo
 * @date 2022/5/15
 *
 */
interface MyView {

    fun editSuccess(response: LoginEntity)
    fun editFailure(message: String)
}