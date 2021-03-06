package com.shibo.overtime.main.fragment.my.model

import android.content.Context
import com.shibo.overtime.base.BaseModel
import com.shibo.overtime.base.BaseModelListener
import com.shibo.overtime.base.Constants
import com.shibo.overtime.login.model.entity.LoginEntity

/**
 * @author shibo
 * @date 2022/5/16
 *
 */
class MyInfoModel: BaseModel<LoginEntity> {

    constructor(context: Context, listener: BaseModelListener<LoginEntity>):super(context){
        setListener(listener)
        setHeader(context)
    }

  override fun getUrl(): String {
      return Constants.URL_INFO
  }

  override fun getClazz(): Class<LoginEntity> {
      return LoginEntity::class.java
  }
}