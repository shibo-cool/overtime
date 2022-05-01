package com.shibo.overtime.main.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.shibo.overtime.base.BaseFragment
import com.shibo.overtime.main.fragment.approval.ApprovalFragment
import com.shibo.overtime.main.fragment.clock.ClockFragment
import com.shibo.overtime.main.fragment.my.MyFragment
import com.shibo.overtime.main.fragment.notes.NotesFragment

class FragmentAdapter: FragmentStatePagerAdapter {

    var mList: ArrayList<BaseFragment> = ArrayList()

    constructor(fm: FragmentManager):super(fm){
        mList.add(ClockFragment())
        mList.add(NotesFragment())
        mList.add(ApprovalFragment())
        mList.add(MyFragment())
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): BaseFragment {
        return mList[position]
    }

}