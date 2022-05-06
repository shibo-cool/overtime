package com.shibo.overtime.login.view

import com.shibo.overtime.login.model.entity.LoginEntity

/**
 * @author shibo
 * @date 2022/5/6
 *
 */
interface LoginView {

  fun loginSuccess(response: LoginEntity)
  fun loginFailure(message: String)
}