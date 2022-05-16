package com.shibo.overtime.login.model.entity

import com.shibo.overtime.base.BaseEntity
import java.io.Serializable

/**
 * @author shibo
 * @date 2022/5/6
 *
 */
class LoginEntity: BaseEntity() {

  var data: Data? = null

  class Data: Serializable{

    /**
     * token
     */
    var access_token: String? = null

    /**
     * 是否有下级（进入主界面需要知道有没有下级来判断是否展示审批界面）
     */
    var hasApprove: String? = null

    /**
     * id
     */
    var id: String? = null

    /**
     * 工号
     */
    var staffNum: String? = null

    /**
     * 真实姓名
     */
    var realname: String? = null

    /**
     * 头像
     */
    var photo: String? = null

    /**
     * 性别
     */
    var gender: String? = null

    /**
     * 昵称
     */
    var nickname: String? = null
  }
}