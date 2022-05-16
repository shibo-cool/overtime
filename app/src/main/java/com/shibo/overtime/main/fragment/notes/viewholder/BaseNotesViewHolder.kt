package com.shibo.overtime.main.fragment.notes.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.shibo.overtime.main.fragment.notes.viewholder.entity.BaseNotesViewHolderEntity

/**
 * @author shibo
 * @date 2022/5/10
 *
 */
open class BaseNotesViewHolder: RecyclerView.ViewHolder {

  constructor(itemView: View?):super(itemView!!)

  open fun updateData(entity: BaseNotesViewHolderEntity){

  }
}