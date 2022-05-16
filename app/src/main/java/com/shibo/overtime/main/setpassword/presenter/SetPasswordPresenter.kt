package com.shibo.overtime.main.setpassword.presenter

import android.content.Context
import com.shibo.overtime.base.BaseModelListener
import com.shibo.overtime.base.BasePresenter
import com.shibo.overtime.main.fragment.approval.model.entity.AgreeModelEntity
import com.shibo.overtime.main.setpassword.model.SetPasswordModel
import com.shibo.overtime.main.setpassword.view.SetPasswordView

/**
 * @author shibo
 * @date 2022/5/15
 *
 */
class SetPasswordPresenter: BasePresenter {

    var mContext: Context? = null
    var mView: SetPasswordView? = null

    constructor(context: Context, view: SetPasswordView){
        mContext = context
        mView = view
    }

    fun setPassword(oldPassword: String, newPassword: String){
        SetPasswordModel(mContext!!, oldPassword, newPassword, object: BaseModelListener<AgreeModelEntity>{
            override fun onSuccess(response: AgreeModelEntity) {
                if(isSuccess(response)) {
                    mView?.setPasswordSuccess(response)
                } else {
                    mView?.setPasswordFailure(response.info?:"接口返回错误")
                }
            }

            override fun onFailure(message: String) {
                mView?.setPasswordFailure(message)
            }

        }).start()
    }
}