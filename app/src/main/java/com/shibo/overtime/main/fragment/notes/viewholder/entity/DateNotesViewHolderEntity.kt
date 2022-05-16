package com.shibo.overtime.main.fragment.notes.viewholder.entity

/**
 * @author shibo
 * @date 2022/5/12
 *
 */
class DateNotesViewHolderEntity: BaseNotesViewHolderEntity() {

    override var type: Int = NOTES_VIEW_HOLDER_DATE

    var date: String? = null
}