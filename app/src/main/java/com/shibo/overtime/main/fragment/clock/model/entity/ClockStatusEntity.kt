package com.shibo.overtime.main.fragment.clock.model.entity

import com.shibo.overtime.base.BaseEntity
import java.io.Serializable

/**
 * @author shibo
 * @date 2022/5/6
 *
 */
class ClockStatusEntity: BaseEntity() {

  var data: Data? = null

  class Data: Serializable{

    /**
     * 直接上级
     */
    var approver: String? = null

    /**
     * 日期
     */
    var date: String? = null

    /**
     * 当前是否加班状态（1：否 2：是）
     */
    var flag: String? = null

    /**
     * 当前时间（时间戳）
     */
    var nowTime: String? = null

    /**
     * 加班原因
     */
    var reason: String? = null

    /**
     * 加班开始时间
     */
    var startTime: String? = null
  }
}