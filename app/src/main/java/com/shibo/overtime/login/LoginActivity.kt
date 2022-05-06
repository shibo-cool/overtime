package com.shibo.overtime.login

import android.content.Context
import android.content.Intent
import android.widget.EditText
import android.widget.TextView
import com.shibo.overtime.R
import com.shibo.overtime.base.BaseActivity
import com.shibo.overtime.login.model.entity.LoginEntity
import com.shibo.overtime.login.presenter.LoginPresenter
import com.shibo.overtime.login.view.LoginView
import com.shibo.overtime.main.MainActivity

/**
 * 登录页
 * @author shibo
 * @date 2022/4/26
 */
class LoginActivity : BaseActivity(), LoginView {

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
    private var mEtUser: EditText? = null

    /**
     * 密码输入框
     */
    private var mEtPassword: EditText? = null

    /**
     * 登录按钮
     */
    private var mBtnLogin: TextView? = null

    var mPresenter: LoginPresenter? = null

    override fun getContentView(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        mEtUser = findViewById(R.id.et_user)
        mEtPassword = findViewById(R.id.et_password)
        mBtnLogin = findViewById(R.id.btn_login)
    }

    override fun initData() {
        mPresenter = LoginPresenter(this, this)
        // 若已保存了帐号和Token，则不需要登录直接进入主页面
        if(mPresenter?.isNeedLogin() == false){
            MainActivity.start(this@LoginActivity)
        }
    }

    override fun setListener() {

        // 点击登录按钮
        mBtnLogin?.setOnClickListener {

            mPresenter?.requestLogin(mEtUser?.text.toString(), mEtPassword?.text.toString())

        }
    }

    override fun loginSuccess(response: LoginEntity) {
        MainActivity.start(this@LoginActivity)
    }

    override fun loginFailure(message: String) {

    }


}