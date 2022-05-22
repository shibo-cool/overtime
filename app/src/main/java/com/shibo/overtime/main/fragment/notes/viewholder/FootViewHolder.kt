package com.shibo.overtime.main.fragment.notes.viewholder

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.shibo.overtime.R
import com.shibo.overtime.main.fragment.notes.viewholder.entity.BaseNotesViewHolderEntity
import com.shibo.overtime.main.listener.OnLoadMoreListener

/**
 * @author shibo
 * @date 2022/5/22
 *
 */
class FootViewHolder: BaseNotesViewHolder{

    companion object{

      // 正在加载
      const val LOADING = 1

      // 加载完成
      const val LOADING_COMPLETE = 2

      @JvmStatic
      fun getContentView(): Int{
        return R.layout.viewholder_notes_clock
      }
    }

    private var tvLoading: TextView? = null
    private var llEnd: LinearLayout? = null

    constructor(itemView: View?) :super(itemView!!){
        tvLoading = itemView.findViewById(R.id.tv_load_more)
        llEnd = itemView.findViewById(R.id.tv_no_more)
    }

    override fun updateData(entity: BaseNotesViewHolderEntity, loadState: Int, listener: OnLoadMoreListener) {
        super.updateData(entity, loadState, listener)
        when(loadState){
            LOADING -> {
                tvLoading?.visibility = View.VISIBLE
                llEnd?.visibility = View.GONE
                listener?.loadMore()
            }
            else -> {
                tvLoading?.visibility = View.GONE
                llEnd?.visibility = View.VISIBLE
            }
        }
    }
}