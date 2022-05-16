package com.shibo.overtime.main.fragment.approval.model

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
class AgreeModel: BaseModel<AgreeModelEntity> {

    constructor(context: Context, status: String, ids: String, listener: BaseModelListener<AgreeModelEntity>):super(context){
        setListener(listener)
        setHeader(context)
        addParam("status", status)
        addParam("ids", ids)
    }

  override fun getUrl(): String {
      return Constants.URL_APPROVAL_PASS
  }

  override fun getClazz(): Class<AgreeModelEntity> {
      return AgreeModelEntity::class.java
  }


}