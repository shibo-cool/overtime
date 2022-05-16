package com.shibo.overtime.main.fragment.notes.viewholder.entity

/**
 * @author shibo
 * @date 2022/5/12
 *
 */
class ClockNotesViewHolderEntity: BaseNotesViewHolderEntity() {

    override var type: Int = NOTES_VIEW_HOLDER_NOTE

    /**
     * 加班日期
     */
    var day: String? = null

    /**
     * 加班周几
     */
    var week: String? = null

    /**
     * 加班时间段
     */
    var timeStr: String? = null

    /**
     * 加班（或调休）持续时长
     */
    var totalTime: String? = null

    /**
     * 加班月份
     */
    var month: String? = null

    // 审核结果代码
    var status: String? = null

    // 加班理由
    var reason: String? = null

    /**
     * 是否展开加班原因
     */
    var isOpen: Boolean? = false
}