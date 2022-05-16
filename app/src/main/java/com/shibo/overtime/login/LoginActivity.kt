package com.shibo.overtime.login

import android.content.Context
import android.content.Intent
import android.os.Looper
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.shibo.overtime.R
import com.shibo.overtime.base.BaseActivity
import com.shibo.overtime.login.model.entity.LoginEntity
import com.shibo.overtime.login.presenter.LoginPresenter
import com.shibo.overtime.login.view.LoginView
import com.shibo.overtime.main.MainActivity
import com.shibo.overtime.widget.MyToast

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
        mEtUser?.setText("129871")
        mEtPassword?.setText("123456")

    }

    override fun initData() {
        mPresenter = LoginPresenter(this, this)
        // 若已保存了帐号和Token，则不需要登录直接进入主页面
        if(mPresenter?.isNeedLogin() == false){
            MainActivity.start(this@LoginActivity)
        }
    }

    override fun setListener() {

        val userId: String = mEtUser?.text.toString()
        val psw: String = mEtPassword?.text.toString()
        if(TextUtils.isEmpty(userId) || TextUtils.isEmpty(psw)){
            MyToast.showToast(this, "用户名或密码不能为空")
            return
        }

        // 点击登录按钮
        mBtnLogin?.setOnClickListener {
            showLoading(true)
            mPresenter?.requestLogin(userId, psw)

        }
    }

    override fun loginSuccess(response: LoginEntity) {
        showLoading(false)
        MainActivity.start(this@LoginActivity)
    }

    override fun loginFailure(message: String) {
        showLoading(false)
        MyToast.showToast(this, message)
    }


}