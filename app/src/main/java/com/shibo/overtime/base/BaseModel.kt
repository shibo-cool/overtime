package com.shibo.overtime.base

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.shibo.overtime.main.MainActivity
import com.shibo.overtime.tool.SharedPreferencesUtil
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

    private val request = Request.Builder()

    /**
     * 创建表单请求参数
     */
    private var builder: FormBody.Builder? = FormBody.Builder()

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
     * 添加请求头
     */
    open fun addHeader(key: String?, value: String?){
        request.addHeader(key?:"", value?:"")
    }

    /**
     * 带id请求头的请求
     */
    open fun setHeader(context: Context){
        val mUnit = SharedPreferencesUtil(context)
        val id = mUnit.getString(SharedPreferencesUtil.USER_ID, "")
        val token = mUnit.getString(SharedPreferencesUtil.USER_TOKEN, "")
        addHeader("id", id)
        addHeader("accesstoken", token)
    }

    /**
     * 开启新线程请求接口
     */
    fun start(){
        val requests = request.url(getAddress() + getUrl())
            .post(builder?.build())
            .build()
        okHttpClient?.newCall(requests)?.enqueue(object : Callback {
            override fun onFailure(p0: Call, p1: IOException) {
                (mContext as MainActivity).runOnUiThread(object: Runnable {
                    override fun run() {
                        mListener?.onFailure("接口请求失败")
                    }
                })
            }

            override fun onResponse(p0: Call, p1: Response) {
                val entity: T? = jsonToBean(p1.body()!!, getClazz())
                if (entity != null) {
                    (mContext as MainActivity).runOnUiThread(object: Runnable{
                        override fun run() {
                            mListener?.onSuccess(entity)
                        }
                    })
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