package com.shibo.overtime.login.presenter

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import android.widget.Toast
import com.shibo.overtime.base.BaseModelListener
import com.shibo.overtime.base.BasePresenter
import com.shibo.overtime.login.model.LoginModel
import com.shibo.overtime.login.model.entity.LoginEntity
import com.shibo.overtime.login.view.LoginView
import com.shibo.overtime.tool.SharedPreferencesUtil

class LoginPresenter: BasePresenter {

    var mContext: Context? = null
    var mView: LoginView? = null

    var mUnit: SharedPreferencesUtil? = null

    constructor(context: Context, view: LoginView){
        mContext = context
        mView = view
        mUnit = SharedPreferencesUtil(context)
    }

    /**
     * 判断是否已登录
     */
    fun isNeedLogin(): Boolean{
        val id = mUnit?.getString(SharedPreferencesUtil.USER_ID, "")
        val token = mUnit?.getString(SharedPreferencesUtil.USER_TOKEN, "")
        return (TextUtils.isEmpty(id) || TextUtils.isEmpty(token))
    }

    /**
     * 登录接口
     */
    fun requestLogin(id: String, psw: String){

        LoginModel(mContext!!, id, psw, object : BaseModelListener<LoginEntity>{
            override fun onSuccess(response: LoginEntity) {
                if(isSuccess(response)) {
                    mUnit?.setValue(SharedPreferencesUtil.USER_ID, id)
                    mUnit?.setValue(SharedPreferencesUtil.USER_TOKEN, response.data?.access_token?:"")
                    mView?.loginSuccess(response)
                } else {
                    mView?.loginFailure(response.info?:"接口返回错误")
                }
            }

            override fun onFailure(message: String) {
                mView?.loginFailure(message)
            }


        }).start()
    }
}