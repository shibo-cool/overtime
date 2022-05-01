package com.shibo.overtime.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager


class CustomerViewPager: ViewPager {

    private var mNoScroll = true

    constructor(context: Context) : super(context)

    constructor(context:Context, attrs: AttributeSet) : super(context, attrs)

    //调用此方法 参数为false 即可禁止滑动
    fun setNoScroll(noScroll: Boolean) {
        mNoScroll = noScroll
    }

    override fun scrollTo(x: Int, y: Int) {
//        if(noScroll){  //加上判断无法用 setCurrentItem 方法切换
        super.scrollTo(x, y)
//        }
    }

    override fun onTouchEvent(arg0: MotionEvent?): Boolean {
        return if (!mNoScroll) false else super.onTouchEvent(arg0)
    }

    override fun onInterceptTouchEvent(arg0: MotionEvent?): Boolean {
        return if (!mNoScroll) false else super.onInterceptTouchEvent(arg0)
    }
}