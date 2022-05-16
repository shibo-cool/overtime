package com.shibo.overtime.main.fragment.approval.model.entity

import com.shibo.overtime.base.BaseEntity
import java.io.Serializable

/**
 * @author shibo
 * @date 2022/5/15
 *
 */
class ApprovalModelEntity: BaseEntity() {

    var data: Data? = null

    class Data: Serializable{

        var approveList: List<ApproveList>? = null

        var curretPage: String? = null

        var hasNext: String? = null

        var total: String? = null
    }

    class ApproveList: Serializable{

        var Yd: String? = null

        var dateStr: String? = null

        var id: String? = null

        var isCheck: Boolean? = false

        var name: String? = null

        var reason: String? = null

        var time: String? = null

        var timeStr: String? = null

        var timeTotal: String? = null

        var week: String? = null
    }
}