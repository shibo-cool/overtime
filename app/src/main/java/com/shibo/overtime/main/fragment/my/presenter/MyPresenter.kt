package com.shibo.overtime.main.fragment.my.presenter

import android.content.Context
import com.shibo.overtime.base.BaseModelListener
import com.shibo.overtime.base.BasePresenter
import com.shibo.overtime.login.model.entity.LoginEntity
import com.shibo.overtime.main.fragment.my.model.MyModel
import com.shibo.overtime.main.fragment.my.view.MyView

/**
 * @author shibo
 * @date 2022/5/15
 *
 */
class MyPresenter: BasePresenter {

    var mContext: Context? = null
    var mView: MyView? = null

    constructor(context: Context, view: MyView){
        mContext = context
        mView = view
    }

    fun edit(photo: String, nickname: String, gender: Int){
        MyModel(mContext!!, photo, nickname, gender, object: BaseModelListener<LoginEntity>{
            override fun onSuccess(response: LoginEntity) {
                if(isSuccess(response)) {
                    mView?.editSuccess(response)
                } else {
                    mView?.editFailure(response.message?:"接口返回失败")
                }
            }

            override fun onFailure(message: String) {
                mView?.editFailure(message)
            }

        })
    }
}