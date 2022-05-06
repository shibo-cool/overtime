package com.shibo.overtime.base

import android.view.View

/**
 * @author shibo
 * @date 2022/5/6
 *
 */
interface FragmentInitView {

    fun getContentView(): Int
    fun initView(view: View)
    fun initData()
    fun setListener()
}