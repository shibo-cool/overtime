package com.shibo.overtime.main.fragment.notes.presenter

import android.content.Context
import com.shibo.overtime.base.BaseModelListener
import com.shibo.overtime.base.BasePresenter
import com.shibo.overtime.main.fragment.notes.adapter.NotesAdapter
import com.shibo.overtime.main.fragment.notes.model.NotesModel
import com.shibo.overtime.main.fragment.notes.model.entity.NotesModelEntity
import com.shibo.overtime.main.fragment.notes.view.NotesView
import com.shibo.overtime.main.fragment.notes.viewholder.entity.BaseNotesViewHolderEntity
import com.shibo.overtime.main.fragment.notes.viewholder.entity.ClockNotesViewHolderEntity
import com.shibo.overtime.main.fragment.notes.viewholder.entity.DateNotesViewHolderEntity

/**
 * @author shibo
 * @date 2022/5/9
 * 加班记录页的presenter
 */
class NotesPresenter: BasePresenter {

    var mContext: Context? = null
    var mView: NotesView? = null
    var lists: MutableList<BaseNotesViewHolderEntity> = ArrayList()
    var mAdapter: NotesAdapter? = null

    constructor(context: Context, view: NotesView, adapter: NotesAdapter?){
        mContext = context
        mView = view
        mAdapter = adapter
    }

    /**
     * 获取加班记录
     */
    fun requestNotes(page: Int){

        NotesModel(mContext!!, page, object: BaseModelListener<NotesModelEntity>{
            override fun onSuccess(response: NotesModelEntity) {
                if(isSuccess(response)){
                    mView?.notesSuccess(response, response.data?.page?.canLoad?:0, page == 0)
                    val list = response.data?.filterData
                    if(page == 0){
                        lists.clear()
                    }
                    if (list != null) {
                        for(item in list){
                            val entity = DateNotesViewHolderEntity()
                            entity.date = item.Ym
                            lists.add(entity)
                            val listNote = item.monthdata
                            if (listNote != null) {
                                for(note in listNote){
                                    val clock = ClockNotesViewHolderEntity()
                                    clock.day = note.day
                                    clock.week = note.week
                                    clock.timeStr = note.timeStr
                                    clock.totalTime = note.totalTime
                                    clock.month = note.month
                                    clock.status = note.status
                                    clock.reason = note.reason
                                    clock.isOpen = note.isOpen
                                    lists.add(clock)
                                }
                            }
                        }
                    }
                    mAdapter?.setData(lists)
                } else {
                    mView?.notesFailure("接口返回失败")
                }
            }

            override fun onFailure(message: String) {
                mView?.notesFailure(message)
            }

        }).start()
    }
}