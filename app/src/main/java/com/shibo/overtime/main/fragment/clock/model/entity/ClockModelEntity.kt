package com.shibo.overtime.main.fragment.clock.model.entity

import com.shibo.overtime.base.BaseEntity
import java.io.Serializable

class ClockModelEntity: BaseEntity() {

    var data: Data? = null

    class Data: Serializable{

        /**
         * 加班状态(0:未开始加班   1：已开始加班)
         */
        var flag = 0

        /**
         * 加班开始时间
         */
        var startTime: Long = 0

        /**
         * 时间
         */
        var nowTime: Long = 0

        /**
         * 日期
         */
        var date: String? = null

        /**
         * 直接上级
         */
        var approver: String? = null

        /**
         * 加班原因
         */
        var reason: String? = null
    }
}