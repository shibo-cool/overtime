package com.shibo.overtime.main.fragment.clock.model

import android.content.Context
import com.shibo.overtime.base.BaseModel
import com.shibo.overtime.base.BaseModelListener
import com.shibo.overtime.base.Constants
import com.shibo.overtime.main.fragment.clock.model.entity.ClockStatusEntity

/**
 * @author shibo
 * @date 2022/5/4
 * 获取当前加班状态和数据
 */
class ClockModel: BaseModel<ClockStatusEntity> {

    constructor(context: Context, flag: String, reason: String, listener: BaseModelListener<ClockStatusEntity>) :super(context) {
        setListener(listener)
        addParam("flag", flag)
        addParam("reason", reason)
    }

    override fun getUrl(): String {
        return Constants.URL_ADD_RECORD
    }

    override fun getClazz(): Class<ClockStatusEntity> {
        return ClockStatusEntity::class.java
    }
}