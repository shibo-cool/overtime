package com.shibo.overtime.base

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException


/**
 * 请求接口基类
 * @author shibo
 * @date 2022/4/26
 */
abstract class BaseModel<T: BaseEntity> {

    private var mListener: BaseModelListener<T>? = null

    private var mContext: Context? = null

    private var okHttpClient = OkHttpClient.getInstance()

    /**
     * 创建表单请求参数
     */
    private var builder: FormBody.Builder? = FormBody.Builder()

    private var body: FormBody? = builder?.build()

    constructor(context: Context){
        mContext = context

    }

    open fun setListener(listener: BaseModelListener<T>) {
        mListener = listener
    }
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
        val request = Request.Builder()
            .url(getAddress() + getUrl())
            .post(body)
            .build()
        okHttpClient?.newCall(request)?.enqueue(object : Callback {
            override fun onFailure(p0: Call, p1: IOException) {
                mListener?.onFailure("接口请求失败")
            }

            override fun onResponse(p0: Call, p1: Response) {
//                val entity = createInstance(getClazz())
                val entity: T? = jsonToBean(p1.body()!!, getClazz())
                if (entity != null) {
//                    entity = JSON.parseObject(p1.body()?.string())
                    mListener?.onSuccess(entity)
                }
            }

        })
    }

    /**
     * 将返回的ResponseBody转为实体类
     */
    private fun jsonToBean(body: ResponseBody, clazz: Class<T>?): T? {
        var obj: T? = null
        val a = body.string()
        val gson = Gson()
        try {
            obj = gson.fromJson(a, clazz)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return obj
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