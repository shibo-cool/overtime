package com.shibo.overtime.main.fragment.clock

import android.content.Context
import android.os.SystemClock
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.Chronometer
import android.widget.Chronometer.OnChronometerTickListener
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.shibo.overtime.R
import com.shibo.overtime.base.BaseFragment
import com.shibo.overtime.main.fragment.clock.model.ClockModel
import com.shibo.overtime.main.fragment.clock.model.entity.ClockModelEntity
import com.shibo.overtime.main.fragment.clock.presenter.ClockPresenter
import com.shibo.overtime.main.fragment.clock.view.ClockView
import com.shibo.overtime.widget.SureAndCancelDialog

class ClockFragment: BaseFragment(), ClockView, SureAndCancelDialog.DialogListener {

    var mPresenter: ClockPresenter? = null

    /**
     * 日期
     */
    private var mTvDate: TextView? = null

    /**
     * 计时器
     */
    private var mTime: Chronometer? = null

    /**
     * 展示时间（倒计时）
     */
    private var mTvTimeShow: TextView? = null

    /**
     * 审批人
     */
    private var mTvApproval: TextView? = null

    /**
     * 加班原因
     */
    private var mTvReason: EditText? = null

    /**
     * 加班原因字数
     */
    private var mTvReasonNum: TextView? = null

    /**
     * 加班按钮
     */
    private var mTvBtnClock: TextView? = null

    override fun getContentView(): Int {
        return R.layout.fragment_clock
    }

    override fun initView(view: View) {
        mTvDate = view.findViewById(R.id.tv_date)
        mTime = view.findViewById(R.id.tv_time)
        mTvTimeShow = view.findViewById(R.id.tv_time_show)
        mTvApproval = view.findViewById(R.id.tv_approver)
        mTvReason = view.findViewById(R.id.reason)
        mTvReasonNum = view.findViewById(R.id.reason_count)
        mTvBtnClock = view.findViewById(R.id.btn)
        mTvBtnClock?.tag = 1
    }

    override fun initData() {
        mPresenter = ClockPresenter(activity as Context)
        getClockRequest()
    }

    override fun setListener() {

        // 点击开始加班或者结束加班
        mTvBtnClock?.setOnClickListener {
            showDialog(mTvBtnClock?.tag.toString(), mTvReason?.text.toString())
        }

        // 加班时长监听
        mTime?.onChronometerTickListener = OnChronometerTickListener { cArg -> //计时器监听
            val t = SystemClock.elapsedRealtime() - cArg.base
            mTvTimeShow?.text = timeStr(t)
            mTvTimeShow?.tag = t
        }

        // 加班理由输入框输入监听
        mTvReason?.addTextChangedListener(object : TextWatcher {
            private var temp: CharSequence? = null
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                temp = s
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                mTvReasonNum?.text = mTvReason?.text?.length.toString()
                if (temp!!.length > 200) {
                    Toast.makeText(activity, "您输入的字数已超过最大限制", Toast.LENGTH_SHORT).show()
                    //这里需要加超过200字的限制
                }
            }
        })

    }

    /**
     * 点击加班按钮触发弹窗
     */
    private fun showDialog(tag: String, reason: String){
        var msg = if ("1".equals(tag, false)) {
            "是否开始加班！"
        } else {
            timeStrInfo(mTvTimeShow?.tag as Long)
        }
        SureAndCancelDialog(activity as Context, "提示", msg!!, this)
    }

    /**
     * 更新加班状态
     */
    private fun getClockRequest() {
//        mPresenter?.requestGetClock(mTvBtnClock?.tag.toString(), )
    }

    /**
     * 接口返回加班状态成功回调
     */
    override fun clockSuccess(request: ClockModel, response: ClockModelEntity) {
        if(response.code == 200){
            if(response.data != null){
                setBtnChangeColor(response.data?.approver?:"")
                mTvApproval?.text = response.data?.approver?:""
                mTvDate?.text = response.data?.date
                if(1 == response.data?.flag){
                    // 未加班状态
                    mTvBtnClock?.tag = 1
                    mTvBtnClock?.text = "开始加班"
                    mTime?.stop()
                    mTvTimeShow?.text = "00:00:00"
                    mTvReason?.isEnabled = true
                    mTvReason?.setText("")
                } else {
                    // 加班状态
                    mTvBtnClock?.tag = 2
                    mTvBtnClock?.text = "结束加班"
                    mTvReason?.isEnabled = false
                    countTime(response.data?.startTime?:0L, response.data?.nowTime?:0L)
                    mTvReasonNum?.text = response.data?.reason?.length.toString()
                    mTvReason?.setText(response.data?.reason)
                }
            }
        } else {
            // 接口返回异常数据

        }
    }

    /**
     * 设置计时器
     * begins: 开始加班时间
     * ends：现在时间
     * 计算差值即是加班时长
     */
    private fun countTime(begins: Long, ends: Long) {
        val between = ends - begins
        mTime?.base = SystemClock.elapsedRealtime() - between * 1000
        mTime?.start()
    }

    /**
     * 设置加班按钮背景颜色
     */
    private fun setBtnChangeColor(paramString: String) {
        if (TextUtils.isEmpty(paramString)) {
            mTvBtnClock?.isClickable = false
            mTvBtnClock?.setBackgroundResource(R.drawable.btn_oval_oval)
            mTvBtnClock?.isEnabled = false
            return
        } else {
            mTvBtnClock?.isClickable = true
            mTvBtnClock?.setBackgroundResource(R.drawable.btn_oval)
            mTvBtnClock?.isEnabled = true
            return
        }
    }

    /**
     * 接口返回加班状态失败回调
     */
    override fun clockFailure(request: ClockModel, message: String) {

    }

    override fun ok() {
        // 点击加班按钮后弹窗中的ok键
    }

    /**
     * 将计时器的值转化格式展示出来
     */
    private fun timeStr(between: Long): String? {
        val betweens = between / 1000
        val day1 = betweens / (24 * 3600)
        val hour1 = betweens % (24 * 3600) / 3600
        val minute1 = betweens % 3600 / 60
        val second1 = betweens % 60
        val sb = java.lang.StringBuilder()
        if (day1 != 0L) {
            sb.append(hour1 + 24 * day1)
        } else {
            if (hour1 == 0L) {
                sb.append("00")
            } else {
                if (hour1 < 10) {
                    sb.append("0$hour1")
                } else {
                    sb.append(hour1)
                }
            }
        }
        sb.append(":")
        if (minute1 == 0L) {
            sb.append("00")
        } else {
            if (minute1 < 10) {
                sb.append("0$minute1")
            } else {
                sb.append(minute1)
            }
        }
        sb.append(":")
        if (second1 == 0L) {
            sb.append("00")
        } else {
            if (second1 < 10) {
                sb.append("0$second1")
            } else {
                sb.append(second1)
            }
        }
        return sb.toString()
    }

    /**
     * 计算加班时间 要展示在点击结束加班的弹窗中
     */
    private fun timeStrInfo(between: Long): String? {
        val sb = StringBuilder()
        sb.append("本次您的加班时间为")
        val betweens = between / 1000
        val day1 = betweens / (24 * 3600)
        val hour1 = betweens % (24 * 3600) / 3600
        val minute1 = betweens % 3600 / 60
        val second1 = betweens % 60
        if (day1 != 0L) {
            sb.append(hour1 + 24 * day1)
        } else {
            if (hour1 != 0L) {
                sb.append(hour1)
                sb.append("小时")
            }
        }
        if (minute1 != 0L) {
            sb.append(minute1)
        } else {
            sb.append("0")
        }
        sb.append("分钟,是否结束本次加班。")
        return sb.toString()
    }
}