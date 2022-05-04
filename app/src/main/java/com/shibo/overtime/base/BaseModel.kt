package com.shibo.overtime.base

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.*
import java.io.IOException

/**
 * 请求接口基类
 * @author shibo
 * @date 2022/4/26
 */
abstract class BaseModel<T: BaseEntity> {

    /**
     * 接口数据返回监听器
     */
//    var mListener: BaseModelListener<BaseModel, T>? = null

    var mContext: Context? = null

    var okHttpClient = OkHttpClient()

    /**
     * 创建表单请求参数
     */
    var builder: FormBody.Builder? = FormBody.Builder()

    var body: FormBody? = builder?.build()

//    constructor(context: Context, listener: BaseModelListener<BaseModel, T>){
//        mContext = context
//        mListener = listener
//    }

    /**
     * 批量添加请求参数
     *
     * @param params
     */
    fun addJsonParams(params: Map<String, String>){
        for (key in params.keys) {
            addParam(key, params[key])
        }
    }

    /**
     * 添加请求参数
     *
     * @param key
     * @param value
     */
    open fun addParam(key: String?, value: String?) {
        builder?.add(key?:"", value?:"")
    }

    /**
     * 开启新线程请求接口
     */
    fun start(){
        /**
         * 因为本项目需要连接公司内网才能正常使用，所以未连接wifi的一律pass
         */
        if(isWifiConnected(mContext)) {
            val request = Request.Builder()
                .url(getAddress() + getUrl())
                .post(body)
                .build()
            Thread(Runnable {
                try {
                    okHttpClient.newCall(request)?.enqueue(object : Callback {
                        override fun onFailure(p0: Call, p1: IOException) {
//                            mListener?.onFailure(this@BaseModel, "接口请求失败")
                        }

                        override fun onResponse(p0: Call, p1: Response) {
                            val entity = createInstance(getClazz())
                            if (entity != null) {
//                                mListener?.onSuccess(this@BaseModel, entity)
                            }
                        }

                    })
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }).start()
        } else {
//            mListener?.onFailure(this@BaseModel, "请连接公司wifi")
        }
    }

    protected open fun <E> createInstance(cls: Class<E>): E? {
        var obj: E? = null
        obj = try {
            cls.newInstance()
        } catch (e: java.lang.Exception) {
            null
        }
        return obj
    }

    /**
     * 判断wifi网络是否可用
     * 本项目代码必须连接公司WiFi才能正确获取接口
     */
    private fun isWifiConnected(context: Context?) : Boolean{
        if(context != null){
            val mConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            if(mNetworkInfo != null){
                return mNetworkInfo.isAvailable
            }
        }
        return false
    }

    /**
     * 获取服务器地址
     * @return
     */
    open fun getAddress(): String? {
        return Constants.SERVER_ADDRESS()
    }

    /**
     * 接口地址
     */
    abstract fun getUrl():String

    /**
     * 设置返回值格式
     */
    abstract fun getClazz():Class<T>

}