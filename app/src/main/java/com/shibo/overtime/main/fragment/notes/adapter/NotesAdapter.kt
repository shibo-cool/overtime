package com.shibo.overtime.main.fragment.notes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shibo.overtime.main.fragment.notes.viewholder.BaseNotesViewHolder
import com.shibo.overtime.main.fragment.notes.viewholder.FootViewHolder
import com.shibo.overtime.main.fragment.notes.viewholder.NotesClockViewHolder
import com.shibo.overtime.main.fragment.notes.viewholder.NotesDataViewHolder
import com.shibo.overtime.main.fragment.notes.viewholder.entity.BaseNotesViewHolderEntity
import com.shibo.overtime.main.listener.OnLoadMoreListener

/**
 * @author shibo
 * @date 2022/5/10
 *
 */
class NotesAdapter: RecyclerView.Adapter<BaseNotesViewHolder> {

    var mContext: Context? = null
    var mList: MutableList<BaseNotesViewHolderEntity> = ArrayList()
    var mLayoutInflater: LayoutInflater? = null
    var mLoadState: Int? = 1
    var mListener: OnLoadMoreListener? = null

    fun setData(data: List<BaseNotesViewHolderEntity>){
        mList.clear()
        mList.addAll(data)
        notifyDataSetChanged()
    }

    constructor(context: Context, listener: OnLoadMoreListener) : super(){
        mContext = context
        mListener = listener
        mLayoutInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseNotesViewHolder {
        return when(viewType){
            10086 -> FootViewHolder(mLayoutInflater?.inflate(FootViewHolder.getContentView(), parent, false))
            BaseNotesViewHolderEntity.NOTES_VIEW_HOLDER_DATE -> {
                NotesDataViewHolder(mLayoutInflater?.inflate(NotesDataViewHolder.getContentView(), parent, false))
            }
            BaseNotesViewHolderEntity.NOTES_VIEW_HOLDER_NOTE -> {
                NotesClockViewHolder(mLayoutInflater?.inflate(NotesClockViewHolder.getContentView(), parent, false))
            }
            else -> {
                NotesClockViewHolder(mLayoutInflater?.inflate(NotesClockViewHolder.getContentView(), parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: BaseNotesViewHolder, position: Int) {
        holder.updateData(mList[position], mLoadState?:1, mListener!!)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun getItemViewType(position: Int): Int {
        return mList[position].type
    }

}