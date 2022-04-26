package com.shibo.overtime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        initView()
        initData()
        setListener()
    }

    abstract fun getContentView():Int
    abstract fun initView()
    abstract fun initData()
    abstract fun setListener()
}