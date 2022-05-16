package com.shibo.overtime.main.fragment.approval.model

import android.content.Context
import com.shibo.overtime.base.BaseModel
import com.shibo.overtime.base.BaseModelListener
import com.shibo.overtime.base.Constants
import com.shibo.overtime.main.fragment.approval.model.entity.ApprovalModelEntity

/**
 * @author shibo
 * @date 2022/5/15
 *
 */
class ApprovalModel: BaseModel<ApprovalModelEntity> {

    constructor(context: Context, page: Int, listener: BaseModelListener<ApprovalModelEntity>):super(context){
        setListener(listener)
        setHeader(context)
        addParam("page", page.toString())
        /**以前的代码就没有说明type这个字段是啥意思*/
        addParam("type", "1")
    }

    override fun getUrl(): String {
        return Constants.URL_APPROVAL
    }

    override fun getClazz(): Class<ApprovalModelEntity> {
        return ApprovalModelEntity::class.java
    }
}