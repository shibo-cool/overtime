package com.shibo.overtime.main.fragment.clock.presenter

import android.content.Context
import com.shibo.overtime.base.BaseModelListener
import com.shibo.overtime.base.BasePresenter
import com.shibo.overtime.main.fragment.clock.model.ClockModel
import com.shibo.overtime.main.fragment.clock.model.ClockStatusModel
import com.shibo.overtime.main.fragment.clock.model.entity.ClockStatusEntity
import com.shibo.overtime.main.fragment.clock.view.ClockView

class ClockPresenter: BasePresenter {

    var mContext: Context? = null
    var mView: ClockView? = null

    constructor(context: Context, view: ClockView){
        mContext = context
        mView = view
    }

    /**
     * 获取当前加班数据
     */
    fun requestStatus(id: String, token: String){
        ClockStatusModel(mContext!!, id, token, object: BaseModelListener<ClockStatusEntity>{
            override fun onSuccess(response: ClockStatusEntity) {
                if(isSuccess(response)) {
                    mView?.clockSuccess(response)
                } else {
                    mView?.clockFailure(response.info?:"接口返回错误")
                }
            }

            override fun onFailure(message: String) {
                mView?.clockFailure(message)
            }

        })
    }

    /**
     * 点击加班或结束加班
     */
    fun requestGetClock(flag: String, reason: String) {
        ClockModel(mContext!!, flag, reason, object: BaseModelListener<ClockStatusEntity>{
            override fun onSuccess(response: ClockStatusEntity) {
                if(isSuccess(response)) {
                    mView?.clockSuccess(response)
                } else {
                    mView?.clockFailure(response.info?:"接口返回错误")
                }
            }

            override fun onFailure(message: String) {

                mView?.clockFailure(message)
            }

        }).start()

    }
}