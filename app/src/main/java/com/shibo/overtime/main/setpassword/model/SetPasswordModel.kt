package com.shibo.overtime.main.setpassword.model

import android.content.Context
import com.shibo.overtime.base.BaseModel
import com.shibo.overtime.base.BaseModelListener
import com.shibo.overtime.base.Constants
import com.shibo.overtime.main.fragment.approval.model.entity.AgreeModelEntity

/**
 * @author shibo
 * @date 2022/5/15
 *
 */
class SetPasswordModel: BaseModel<AgreeModelEntity> {

    constructor(context: Context, oldPassword: String, newPassword: String, listener: BaseModelListener<AgreeModelEntity>) : super(context){
        setListener(listener)
        setHeader(context)
        addParam("oldPassword", oldPassword)
        addParam("newPassword", newPassword)
    }

    override fun getUrl(): String {
        return Constants.URL_CHAGNE_PWD
    }

    override fun getClazz(): Class<AgreeModelEntity> {
        return AgreeModelEntity::class.java
    }


}