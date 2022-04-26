package com.shibo.overtime

import android.content.Intent
import android.os.Handler

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

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }, 1500)
    }
}