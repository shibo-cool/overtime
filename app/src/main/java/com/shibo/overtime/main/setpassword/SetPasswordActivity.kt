package com.shibo.overtime.main.setpassword

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.shibo.overtime.R
import com.shibo.overtime.base.BaseActivity
import com.shibo.overtime.main.fragment.approval.model.entity.AgreeModelEntity
import com.shibo.overtime.main.setpassword.presenter.SetPasswordPresenter
import com.shibo.overtime.main.setpassword.view.SetPasswordView
import com.shibo.overtime.widget.MyToast

/**
 * @author shibo
 * @date 2022/5/15
 * 设置密码页
 */
class SetPasswordActivity: BaseActivity(), SetPasswordView {

    companion object{
        @JvmStatic
        fun start(context: Context){
            val intent = Intent(context, SetPasswordActivity::class.java)
            context.startActivity(intent)
        }
    }

    private var mBack: ImageView? = null

    private var mEtOldPwd: EditText? = null

    private var mEtNewPassword: EditText? = null
    private var mEtOldPwdClear: ImageView? = null
    private var mEtNewPwdClear: ImageView? = null

    private var mBtnCommit: Button? = null

    private var mIvPswHide: ImageView? = null
    private var mPresenter: SetPasswordPresenter? = null

    /**
     * 密码隐藏
     */
    private var mIsHide = true

    override fun getContentView(): Int {
        return R.layout.activity_set_password
    }

    override fun initView() {
        mBack = findViewById(R.id.back)
        mEtOldPwd = findViewById(R.id.et_old_pwd)
        mEtNewPassword = findViewById(R.id.et_new_password)
        mEtOldPwdClear = findViewById(R.id.et_old_pwd_clear)
        mEtNewPwdClear = findViewById(R.id.et_new_pwd_clear)
        mBtnCommit = findViewById(R.id.btn_commit)
        mIvPswHide = findViewById(R.id.iv_psw_hide)
    }

    override fun initData() {
        mPresenter = SetPasswordPresenter(this, this)
    }

    override fun setListener() {
        mBtnCommit?.setOnClickListener{
            val v1: View = this@SetPasswordActivity.window.peekDecorView()
            closeSoftInput(this@SetPasswordActivity, v1)
            pwdChange()
        }

        mBack?.setOnClickListener{
            val v1: View = this@SetPasswordActivity.window.peekDecorView()
            closeSoftInput(this@SetPasswordActivity, v1)
            finish()
        }
        mIvPswHide?.setOnClickListener{
            mIsHide = if (mIsHide) {
                //如果选中，显示密码
                mIvPswHide?.setImageResource(R.mipmap.icon_password_show)
                mEtNewPassword?.transformationMethod = HideReturnsTransformationMethod.getInstance()
                false
            } else {
                //否则隐藏密码
                mIvPswHide?.setImageResource(R.mipmap.icon_password_hide)
                mEtNewPassword?.transformationMethod = PasswordTransformationMethod.getInstance()
                mEtNewPassword?.tag = true
                true
            }
        }

        mEtOldPwdClear?.setOnClickListener{ mEtOldPwd?.setText("") }

        mEtNewPwdClear?.setOnClickListener{ mEtNewPassword?.setText("") }
    }

    private fun closeSoftInput(activity: Activity, view: View) {
        val imm = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun pwdChange() {
        val oldPwd: String = mEtOldPwd?.text.toString()
        val newPwd: String = mEtNewPassword?.text.toString()
        if (TextUtils.isEmpty(oldPwd) || TextUtils.isEmpty(newPwd)) {
            MyToast.showToast(this, "新老密码不能为空！")
            return
        }
        mPresenter?.setPassword(oldPwd,newPwd)
    }

    override fun setPasswordSuccess(response: AgreeModelEntity) {

        if (!"200".equals(response.status, false)) {
            MyToast.showToast(this, response.info?:"")
        } else if ("200".equals(response.status, false)) {
            finish()
        }
    }

    override fun setPasswordFailure(message: String) {
        MyToast.showToast(this, message)
    }
}