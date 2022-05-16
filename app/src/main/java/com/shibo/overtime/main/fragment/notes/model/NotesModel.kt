package com.shibo.overtime.main.fragment.notes.model

import android.content.Context
import com.shibo.overtime.base.BaseModel
import com.shibo.overtime.base.BaseModelListener
import com.shibo.overtime.base.Constants
import com.shibo.overtime.main.fragment.notes.model.entity.NotesModelEntity

/**
 * @author shibo
 * @date 2022/5/9
 *
 */
class NotesModel: BaseModel<NotesModelEntity> {

    constructor(context: Context, page: Int, listener: BaseModelListener<NotesModelEntity>) : super(context){
        setListener(listener)
        setHeader(context)
        addParam("page", page.toString())
    }

    override fun getUrl(): String {
        return Constants.URL_RECORD
    }

    override fun getClazz(): Class<NotesModelEntity> {
        return NotesModelEntity::class.java
    }
}