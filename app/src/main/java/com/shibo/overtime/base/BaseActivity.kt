package com.shibo.overtime.base

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity(), ContentView, InitView, InitData, SetListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        initView()
        initData()
        setListener()
    }
}