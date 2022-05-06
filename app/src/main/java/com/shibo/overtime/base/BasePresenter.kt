package com.shibo.overtime.base

/**
 * @author shibo
 * @date 2022/5/6
 *
 */
open class BasePresenter {

  fun isSuccess(response: BaseEntity): Boolean{
    return "200".equals(response.status,false) || 200 == response.code
  }
}