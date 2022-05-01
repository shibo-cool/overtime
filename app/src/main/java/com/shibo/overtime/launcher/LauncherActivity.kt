package com.shibo.overtime.launcher

import android.content.Intent
import android.os.Handler
import com.shibo.overtime.R
import com.shibo.overtime.base.BaseActivity
import com.shibo.overtime.login.LoginActivity

/**
 * 载入界面
 * 倒计时1.5秒后进入登录页
 */
class LauncherActivity: BaseActivity() {

    var handler: Handler? = Handler()

    override fun getContentView(): Int {
        return R.layout.activity_launcher
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun setListener() {
        handler?.postDelayed({

            LoginActivity.start(this@LauncherActivity)
            finish()

        }, 1500)
    }
}