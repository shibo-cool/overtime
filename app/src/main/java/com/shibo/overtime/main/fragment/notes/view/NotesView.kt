package com.shibo.overtime.main.fragment.notes.view

import com.shibo.overtime.main.fragment.notes.model.entity.NotesModelEntity

/**
 * @author shibo
 * @date 2022/5/9
 *
 */
interface NotesView {

    fun notesSuccess(response: NotesModelEntity, canLoad: Int, isFirst: Boolean)
    fun notesFailure(message: String)
}