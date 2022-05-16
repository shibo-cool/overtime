package com.shibo.overtime.main.fragment.notes.viewholder

import android.view.View
import android.widget.TextView
import com.shibo.overtime.R
import com.shibo.overtime.main.fragment.notes.viewholder.entity.BaseNotesViewHolderEntity
import com.shibo.overtime.main.fragment.notes.viewholder.entity.DateNotesViewHolderEntity

/**
 * @author shibo
 * @date 2022/5/10
 * 日期ViewHolder
 */
class NotesDataViewHolder: BaseNotesViewHolder {

    companion object{

        @JvmStatic
        fun getContentView(): Int{
            return R.layout.viewholder_notes_date
        }
    }

        var mTitle: TextView? = null

    constructor(itemView: View?):super(itemView!!){

        mTitle = itemView.findViewById(R.id.tv_title)
    }

    override fun updateData(entity: BaseNotesViewHolderEntity){
        val title = (entity as DateNotesViewHolderEntity).date
        mTitle?.text = title
    }
}