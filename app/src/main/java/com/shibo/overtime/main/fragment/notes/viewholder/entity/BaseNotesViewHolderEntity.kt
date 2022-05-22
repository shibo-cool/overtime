package com.shibo.overtime.main.fragment.notes.viewholder.entity

import java.io.Serializable

/**
 * @author shibo
 * @date 2022/5/12
 *
 */
open class BaseNotesViewHolderEntity: Serializable {

    companion object{
        const val NOTES_VIEW_HOLDER_DATE = 0
        const val NOTES_VIEW_HOLDER_NOTE = 1
    }

    open var type = NOTES_VIEW_HOLDER_DATE

    open var hasNext: Boolean = false
}