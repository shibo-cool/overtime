package com.shibo.overtime.main.fragment.approval.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shibo.overtime.R
import com.shibo.overtime.main.fragment.approval.model.entity.ApprovalModelEntity

/**
 * @author shibo
 * @date 2022/5/15
 *
 */
class ApprovalAdapter: RecyclerView.Adapter<ApprovalAdapter.ApprovalViewHolder> {

    private val mList: MutableList<ApprovalModelEntity.ApproveList> = ArrayList()
    private var mContext: Context? = null
    var mChange: ApprovalAdapterChangeListener? = null

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

    /**
     * 点击某一项的选择时，判断当前是否全选
     */
    fun setChange(change: ApprovalAdapterChangeListener){
        mChange = change
    }

    /**
     * 点击全选
     */
    @SuppressLint("NotifyDataSetChanged")
    fun setCheckAll(param: Boolean){
        val localIterator = mList.iterator()
        while (localIterator.hasNext()){
            localIterator.next().isCheck = param
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApprovalViewHolder {
        return ApprovalViewHolder(LayoutInflater.from(mContext).inflate(ApprovalViewHolder.getContentView(), parent, false))
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ApprovalViewHolder, position: Int) {
        holder.updateData(mList[position], mChange, getSelectData().size == mList.size) { notifyDataSetChanged() }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    /**
     * 获取当前已选择的项列表，用于判断是否全选
     */
    private fun getSelectData(): List<ApprovalModelEntity.ApproveList>{
        val list = ArrayList<ApprovalModelEntity.ApproveList>()
        val localIterator = mList.iterator()
        while (localIterator.hasNext()){
            val bean = localIterator.next()
            if(bean.isCheck == true){
                list.add(bean)
            }
        }
        return list
    }

    /**
     * 获取加已选班项，点击同意或拒绝的时候需要使用
     */
    fun getSelectIds(): String{
        var selects = StringBuilder()
        val localIterator = mList.iterator()
        while (localIterator.hasNext()){
            val bean = localIterator.next()
            if(bean.isCheck == true){
                selects.append(bean.id + ",")
            }
        }
        return selects.toString()
    }

    class ApprovalViewHolder: RecyclerView.ViewHolder{

        companion object{
            fun getContentView(): Int{
                return R.layout.view_holder_approval
            }
        }

        private var mRbName: CheckBox? = null
        private var mTvDate: TextView? = null
        private var mTvReason: TextView? = null
        private var mTvReasonPre: TextView? = null
        private var mTvTime: TextView? = null
        private var mLine: TextView? = null

        constructor(itemView: View?):super(itemView!!){
            mRbName = itemView.findViewById(R.id.rb_name)
            mTvDate = itemView.findViewById(R.id.tv_date)
            mTvReason = itemView.findViewById(R.id.tv_reason)
            mTvReasonPre = itemView.findViewById(R.id.tv_reason_pre)
            mTvTime = itemView.findViewById(R.id.tv_time)
            mLine = itemView.findViewById(R.id.line)
        }

        fun updateData(bean: ApprovalModelEntity.ApproveList, change: ApprovalAdapterChangeListener?, checkAll: Boolean, notifyDataSetChanged: () -> Unit) {
            mRbName?.text = bean.name
            mTvReason?.text = bean.reason
            mTvDate?.text = bean.dateStr
            mTvTime?.text = bean.timeStr
            mRbName?.setOnCheckedChangeListener(null)
            mRbName?.isChecked = (bean.isCheck == true)
            mRbName?.setOnCheckedChangeListener(object: CompoundButton.OnCheckedChangeListener{
                override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                    bean.isCheck = p1
                    if(change != null){
                        change.checkedChangeAll(checkAll)
                    }
                    notifyDataSetChanged
                }

            })
        }
    }

    interface ApprovalAdapterChangeListener{
        fun checkedChangeAll(param: Boolean)
    }
}