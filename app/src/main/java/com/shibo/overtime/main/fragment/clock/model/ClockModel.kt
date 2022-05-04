package com.shibo.overtime.main.fragment.clock.model

import android.content.Context
import com.shibo.overtime.base.BaseModel
import com.shibo.overtime.base.BaseModelListener
import com.shibo.overtime.base.Constants
import com.shibo.overtime.main.fragment.clock.model.entity.ClockModelEntity

/**
 * 获取当前加班状态和数据
 */
class ClockModel: BaseModel<ClockModelEntity> {

    constructor(context: Context, flag: String, reason: String, listener: BaseModelListener<ClockModel, ClockModelEntity>)
//            :super(context, listener)
    {
        addParam("flag", flag)
        addParam("reason", reason)
    }

    override fun getUrl(): String {
        return Constants.URL_ADD_RECORD
    }

    override fun getClazz(): Class<ClockModelEntity> {
        return ClockModelEntity::class.java
    }
}