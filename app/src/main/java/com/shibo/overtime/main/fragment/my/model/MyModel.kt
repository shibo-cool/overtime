package com.shibo.overtime.main.fragment.my.model

import android.content.Context
import com.shibo.overtime.base.BaseModel
import com.shibo.overtime.base.BaseModelListener
import com.shibo.overtime.base.Constants
import com.shibo.overtime.login.model.entity.LoginEntity

/**
 * @author shibo
 * @date 2022/5/15
 *
 */
class MyModel: BaseModel<LoginEntity> {

    constructor(context: Context, photo: String, nickname: String, gender: Int, listener: BaseModelListener<LoginEntity>):super(context){
        setHeader(context)
        setListener(listener)
        addParam("photo", photo)
        addParam("nickname", nickname)
        addParam("gender", gender.toString())
    }

    override fun getUrl(): String {
        return Constants.URL_EDIT
    }

    override fun getClazz(): Class<LoginEntity> {
        return LoginEntity::class.java
    }
}