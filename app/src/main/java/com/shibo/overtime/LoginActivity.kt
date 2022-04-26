package com.shibo.overtime

import android.widget.EditText
import android.widget.TextView

/**
 * 登录页
 * @author shibo
 * @date 2022/4/26
 */
class LoginActivity : BaseActivity() {

    /**
     * 帐号输入框
     */
    var mEtUser: EditText? = null

    /**
     * 密码输入框
     */
    var mEtPassword: EditText? = null

    /**
     * 登录按钮
     */
    var mBtnLogin: TextView? = null

    override fun getContentView(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        mEtUser = findViewById(R.id.et_user)
        mEtPassword = findViewById(R.id.et_password)
        mBtnLogin = findViewById(R.id.btn_login)
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

    override fun setListener() {
        TODO("Not yet implemented")
    }


}