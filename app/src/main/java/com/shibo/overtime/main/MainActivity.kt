package com.shibo.overtime.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.google.android.material.tabs.TabLayout
import com.shibo.overtime.R
import com.shibo.overtime.base.BaseActivity
import com.shibo.overtime.base.ShowLoadingListener
import com.shibo.overtime.main.adapter.FragmentAdapter
import com.shibo.overtime.widget.CustomerViewPager

class MainActivity : BaseActivity(), ShowLoadingListener {

    companion object{

        @JvmStatic
        fun start(context: Context){
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }

    }

    private var mTab: TabLayout? = null
    private var mViewPager: CustomerViewPager? = null
    private var mAdapter: FragmentAdapter? = null

    /**
     * tab
     */
    val tabSelected = arrayOf(
        R.mipmap.icon_tab_clock_selected,
        R.mipmap.icon_tab_notes_selected,
        R.mipmap.icon_tab_approval_selected,
        R.mipmap.icon_tab_my_selected)
    private val tabUnSelected = arrayOf(
        R.mipmap.icon_tab_clock_default,
        R.mipmap.icon_tab_notes_default,
        R.mipmap.icon_tab_approval_default,
        R.mipmap.icon_tab_my_default)

    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        mTab = findViewById(R.id.main_tab)
        mViewPager = findViewById(R.id.main_viewpager)
    }

    override fun initData() {
        val lp = mTab?.getChildAt(0)?.layoutParams;
        lp?.width = ViewGroup.LayoutParams.MATCH_PARENT
        mTab?.getChildAt(0)?.layoutParams = lp;
        mTab?.setupWithViewPager(mViewPager)
        mAdapter = FragmentAdapter(supportFragmentManager, this)
        mViewPager?.adapter = mAdapter
        mViewPager?.setCurrentItem(0,false)
        setCustomIcon()
        // 禁止滑动翻页
        mViewPager?.setNoScroll(false)
    }

    override fun setListener() {
        mTab?.setOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val image = tab?.customView?.findViewById<ImageView>(R.id.iv_tab)
                val position = tab?.customView?.tag
                image?.setImageResource(tabSelected[position as Int])
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val image = tab?.customView?.findViewById<ImageView>(R.id.iv_tab)
                val position = tab?.customView?.tag
                image?.setImageResource(tabUnSelected[position as Int])
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    /**
     * 设置自定义位置图标
     */
    private fun setCustomIcon() {
        for (i in 0 until 4){
            var tab:TabLayout.Tab = mTab?.newTab()!!
            mTab?.getTabAt(i)?.customView = makeTabView(i)
//            if(tab.view.layoutParams is LinearLayout.LayoutParams){
//                (tab.view.layoutParams as LinearLayout.LayoutParams).width = 0;
//                (tab.view.layoutParams as LinearLayout.LayoutParams).weight = 1.0f;
//            }
            mTab?.addTab(tab)

        }
    }

    /**
     * 引入布局设置图标和标题
     * @param position
     * @return tabView
     */
    private fun makeTabView(position: Int): View {
        val tabView = LayoutInflater.from(this).inflate(R.layout.tab_main,null)
        val imageView = tabView.findViewById<ImageView>(R.id.iv_tab)
        tabView.tag = position
        imageView.setImageResource(tabUnSelected[position])
        if(position == 0){
            imageView.setImageResource(tabSelected[position])
        }
        return tabView;
    }

    override fun showLoadings(show: Boolean) {
        showLoading(show)
    }

}