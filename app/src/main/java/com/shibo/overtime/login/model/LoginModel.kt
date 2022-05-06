package com.shibo.overtime.login.model

import android.content.Context
import com.shibo.overtime.base.BaseModel
import com.shibo.overtime.base.BaseModelListener
import com.shibo.overtime.base.Constants
import com.shibo.overtime.login.model.entity.LoginEntity
import com.shibo.overtime.main.fragment.clock.model.entity.ClockStatusEntity

/**
 * @author shibo
 * @date 2022/5/6
 *
 */
class LoginModel: BaseModel<LoginEntity> {

    constructor(context: Context, id: String, psw: String, listener: BaseModelListener<LoginEntity>) : super(context){
        setListener(listener)
        addParam("id", id)
        addParam("psw", psw)
    }

    override fun getUrl(): String {
        return Constants.URL_LOGIN
    }

    override fun getClazz(): Class<LoginEntity> {
        return LoginEntity::class.java
    }
}