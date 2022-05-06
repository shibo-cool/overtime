package com.shibo.overtime.base

/**
 * @author shibo
 * @date 2022/5/6
 *
 */
interface ActivityInitView {

  fun getContentView(): Int
  fun initView()
  fun initData()
  fun setListener()
}