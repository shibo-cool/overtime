package com.shibo.overtime.login

import android.content.Context
import android.content.Intent
import android.widget.EditText
import android.widget.TextView
import com.shibo.overtime.R
import com.shibo.overtime.base.BaseActivity
import com.shibo.overtime.main.MainActivity

/**
 * 登录页
 * @author shibo
 * @date 2022/4/26
 */
class LoginActivity : BaseActivity() {

    companion object{

        @JvmStatic
        fun start(context: Context){
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }

    }

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

    }

    override fun setListener() {

        // 点击登录按钮
        mBtnLogin?.setOnClickListener {

            MainActivity.start(this@LoginActivity)

        }
    }


}