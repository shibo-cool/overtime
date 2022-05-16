package com.shibo.overtime.widget

import android.app.ActivityManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.shibo.overtime.R
import com.shibo.overtime.tool.SharedPreferencesUtil
import kotlin.system.exitProcess

/**
 * @author shibo
 * @date 2022/5/15
 *
 */
class ExitDialog: DialogFragment(), View.OnClickListener {

    private var mView: View? = null

    private var mTvExit: TextView? = null

    private var mTvCancel: TextView? = null

    private var mUnit: SharedPreferencesUtil? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.fragment_dialog_style)
        mUnit = SharedPreferencesUtil(activity)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = LayoutInflater.from(activity).inflate(R.layout.dialog_exit, null)
        initView()
        setListener()
        return mView
    }

    fun initView() {
        mTvExit = mView!!.findViewById(R.id.tv_exit)
        mTvCancel = mView!!.findViewById(R.id.tv_cancel)
    }

    fun setListener() {
        mTvExit?.setOnClickListener(this)
        mTvCancel?.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        dismiss()
        when (v.id) {
            R.id.tv_exit -> {
                mUnit?.setValue(SharedPreferencesUtil.USER_ID, "")
                mUnit?.setValue(SharedPreferencesUtil.USER_TOKEN, "")

                val am = activity.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
                am.restartPackage(activity.packageName)
                exitProcess(0)
                activity.finish()
            }
            R.id.tv_cancel -> {}
        }
    }
}