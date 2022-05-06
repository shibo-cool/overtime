package com.shibo.overtime.base

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity(), ActivityInitView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        initView()
        initData()
        setListener()
    }

    // todo 后期要加上万一token过期需重新回到登录页的逻辑
}