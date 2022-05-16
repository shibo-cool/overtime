package com.shibo.overtime.base

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.shibo.overtime.R

abstract class BaseActivity: AppCompatActivity(), ActivityInitView {

    var mLoading: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        initView()
        initData()
        setListener()
    }

    override fun initData(){
        mLoading = findViewById(R.id.progressBar2)
    }

    open fun showLoading(show: Boolean) {
        mLoading?.visibility = when(show) {
            true -> View.VISIBLE
            else -> View.GONE
        }
    }

    // todo 后期要加上万一token过期需重新回到登录页的逻辑
}