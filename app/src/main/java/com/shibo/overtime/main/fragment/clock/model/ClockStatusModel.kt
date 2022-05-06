package com.shibo.overtime.main.fragment.clock.model

import android.content.Context
import com.shibo.overtime.base.BaseModel
import com.shibo.overtime.base.BaseModelListener
import com.shibo.overtime.base.Constants
import com.shibo.overtime.main.fragment.clock.model.entity.ClockStatusEntity

/**
 * @author shibo
 * @date 2022/5/6
 *
 */
class ClockStatusModel: BaseModel<ClockStatusEntity> {

    constructor(context: Context ,id: String, token: String, listener: BaseModelListener<ClockStatusEntity>) : super(context){
        setListener(listener);
        addParam("id", id)
        addParam("token", token)
    }

    override fun getUrl(): String {
        return Constants.URL_STATUS
    }

    override fun getClazz(): Class<ClockStatusEntity> {
        return ClockStatusEntity::class.java
    }


}