package com.shibo.overtime.base

import android.os.Handler
import android.os.Looper
import okhttp3.Request
import java.lang.reflect.Field

abstract class BasicModel<T: BaseEntity> {

    protected var handler: Handler? = null
    var listener: BaseModelListener<BasicModel<T>, T>? = null
    protected var mHeaders: Map<String, String>? = null
    protected var mParams: Map<String, String>? = null
//    protected var mRequest: Request? = null
    protected var mBuilder: Request.Builder? = null

    companion object{

        fun setValue(field: Field, obj: Any, value: Any){
            try {
                field.set(obj, value)
            } catch (e: IllegalAccessException){

            }
        }

    }

    fun BasicModel(listener: BaseModelListener<BasicModel<T>, T>){
        this.listener = listener
        mBuilder = Request.Builder()
        handler = Handler(Looper.getMainLooper())
    }

    abstract fun getServerAddress(): String

//    fun start(): BasicModel<T> {
//        if(mHeaders != null){
//            mBuilder?.addHeader(mHeaders?.keys.toString(), mHeaders?.values.toString())
//        }
//
//        if(mBuilder?.method()) == 0 &&
//    }

}