package com.shibo.overtime.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shibo.overtime.R

abstract class BaseFragment: Fragment(), ContentView, InitView, InitData, SetListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getContentView(), container, false)
        initView()
        initData()
        setListener()
    }
}