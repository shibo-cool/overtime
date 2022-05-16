package com.shibo.overtime.main.fragment.notes.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.shibo.overtime.R
import com.shibo.overtime.main.fragment.notes.viewholder.entity.BaseNotesViewHolderEntity
import com.shibo.overtime.main.fragment.notes.viewholder.entity.ClockNotesViewHolderEntity

/**
 * @author shibo
 * @date 2022/5/10
 * 加班项ViewHolder
 */
class NotesClockViewHolder: BaseNotesViewHolder {

    companion object{

        @JvmStatic
        fun getContentView(): Int{
            return R.layout.viewholder_notes_clock
        }
    }

    /**
     * 上层Item项
     */
    private var mLayout: RelativeLayout? = null

    /**
     * 日期
     */
    private var mDay: TextView? = null

    /**
     * 月份
     */
    private var mMonth: TextView? = null

    /**
     * 星期几
     */
    private var mWeek: TextView? = null

    /**
     * 时间段
     */
    private var mPeriod: TextView? = null

    /**
     * 审核标签
     */
    private var mLabel: ImageView? = null

    /**
     * 持续时长
     */
    private var mDuration: TextView? = null

    /**
     * 下拉箭头(点击时要切换向上向下)
     */
    private var mArrow: ImageView? = null

    /**
     * 下层Item项
     */
    private var mReasons: LinearLayout? = null

    /**
     * 加班理由
     */
    private var mReason: TextView? = null

    constructor(itemView: View?): super(itemView!!){
        mLayout = itemView.findViewById(R.id.layout_details)
        mDay = itemView.findViewById(R.id.tv_day)
        mMonth = itemView.findViewById(R.id.tv_month)
        mWeek = itemView.findViewById(R.id.tv_week)
        mPeriod = itemView.findViewById(R.id.tv_range)
        mLabel = itemView.findViewById(R.id.img_range)
        mDuration = itemView.findViewById(R.id.tv_total)
        mArrow = itemView.findViewById(R.id.details)
        mReasons = itemView.findViewById(R.id.reason_details)
        mReason = itemView.findViewById(R.id.tv_reason_editor)
    }

    override fun updateData(entity: BaseNotesViewHolderEntity){
        val entities = entity as ClockNotesViewHolderEntity
        mDay?.text = entities.day
        mMonth?.text = entities.month
        mWeek?.text = entities.week
        mPeriod?.text = entities.timeStr
        mDuration?.text = entities.totalTime
        mReason?.text = entities.reason
//        mLayout?.tag = entities
        when {
            "1".equals(entities.status,false) -> {
                mLabel?.setImageResource(R.drawable.icon_checkpending)
            }
            "2".equals(entities.status,false) -> {
                mLabel?.setImageResource(R.drawable.icon_haveagreed);
            }
            else -> {
                mLabel?.setImageResource(R.drawable.icon_refused);
            }
        }
        if (entities.isOpen == true) {
            mArrow?.setImageResource(R.drawable.icon_retract)
            mReasons?.visibility = View.VISIBLE
        } else {
            mArrow?.setImageResource(R.drawable.icon_open)
            mReasons?.visibility = View.GONE
        }

        mLayout?.setOnClickListener{
            if(mReasons?.visibility == View.GONE){
                mReasons?.visibility = View.VISIBLE
            } else {
                mReasons?.visibility = View.GONE
            }
        }

    }
}