package com.shibo.overtime.main.approvalNote.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shibo.overtime.R
import com.shibo.overtime.main.fragment.approval.model.entity.ApprovalModelEntity

/**
 * @author shibo
 * @date 2022/5/15
 *
 */
class ApprovalNoteAdapter: RecyclerView.Adapter<ApprovalNoteAdapter.ApprovalNoteViewHolder> {


    private val mList: MutableList<ApprovalModelEntity.ApproveList> = ArrayList()
    private var mContext: Context? = null

    constructor(context: Context){
        mContext = context
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<ApprovalModelEntity.ApproveList>?){
        if(list != null) {
            mList.clear()
            mList.addAll(list)
            notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData(list: List<ApprovalModelEntity.ApproveList>?){
        if(list != null){
            mList.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApprovalNoteViewHolder {
        return ApprovalNoteViewHolder(LayoutInflater.from(mContext).inflate(ApprovalNoteViewHolder.getContentView(), parent, false))
    }

    override fun onBindViewHolder(holder: ApprovalNoteViewHolder, position: Int) {
        holder.updateData(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ApprovalNoteViewHolder: RecyclerView.ViewHolder{

        companion object{

            @JvmStatic
            fun getContentView(): Int{
                return R.layout.view_holder_approval_note
            }
        }

        private var rbName: TextView? = null
        private var tvDate: TextView? = null
        private var tvReason: TextView? = null
        private var tvReasonPre: TextView? = null
        private var tvTime: TextView? = null
        private var vLine: View? = null

        constructor(itemView: View):super(itemView){

            rbName = itemView.findViewById(R.id.rbName)
            tvReasonPre = itemView.findViewById(R.id.tvReasonPre)
            tvReason = itemView.findViewById(R.id.tvReason)
            vLine = itemView.findViewById(R.id.line)
            tvDate = itemView.findViewById(R.id.tvDate)
            tvTime = itemView.findViewById(R.id.tvTime)
        }

        fun updateData(bean: ApprovalModelEntity.ApproveList){

        }
    }
}