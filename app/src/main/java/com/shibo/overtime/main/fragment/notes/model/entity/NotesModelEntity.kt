package com.shibo.overtime.main.fragment.notes.model.entity

import com.shibo.overtime.base.BaseEntity
import com.shibo.overtime.tool.PageInfo
import java.io.Serializable

/**
 * @author shibo
 * @date 2022/5/9
 * 加班记录数据类
 */
class NotesModelEntity: BaseEntity() {

  // todo 这个数据类还没对接接口暂时不知道没资格段的意思，后面补上

    var data: Data? = null

    class Data: Serializable {

        var total: Total? = null

        var filterData: List<FilterData>? = null

        var page: PageInfo? = null
    }

    class Total: Serializable {

        var recordTime: String? = null

        var paidLeavelTime: String? = null

        var remainTime: RemainTime? = null
    }

    class RemainTime: Serializable {

        var hour: String? = null

        var minute: String? = null
    }

    class FilterData: Serializable {

        var Ym: String? = null

        var month: String? = null

        var monthdata: List<Monthdata>? = null
    }

    class Monthdata: Serializable {

        var day: String? = null

        var week: String? = null

        var timeStr: String? = null

        var totalTime: String? = null

        var month: String? = null

        // 审核结果代码
        var status: String? = null

        // 加班理由
        var reason: String? = null

        var isOpen: Boolean? = false
    }
}