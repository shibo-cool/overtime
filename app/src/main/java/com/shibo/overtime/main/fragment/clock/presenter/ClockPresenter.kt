package com.shibo.overtime.main.fragment.clock.presenter

import android.content.Context
import com.shibo.overtime.base.BaseModelListener
import com.shibo.overtime.main.fragment.clock.model.ClockModel
import com.shibo.overtime.main.fragment.clock.model.entity.ClockModelEntity
import com.shibo.overtime.main.fragment.clock.view.ClockView

class ClockPresenter {

    var mContext: Context? = null

    constructor(context: Context){
        mContext = context
    }

    /**
     * 获取当前加班状态数据
     */
    fun requestGetClock(flag: String, reason: String, view: ClockView) {

        ClockModel(mContext!!, flag, reason, object: BaseModelListener<ClockModel, ClockModelEntity>{

            override fun onSuccess(request: ClockModel, response: ClockModelEntity) {

                view.clockSuccess(request, response)
            }

            override fun onFailure(request: ClockModel, message: String) {

                view.clockFailure(request, message)
            }

        }).start()

    }
}