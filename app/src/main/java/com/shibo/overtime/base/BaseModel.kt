package com.shibo.overtime.base

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

/**
 * 请求接口基类
 * @author shibo
 * @date 2022/4/26
 */
abstract class BaseModel<T: BaseEntity> {

    /**
     * 请求头
     */
    var headers: Map<String, String>? = null

    /**
     * 请求参数
     */
    var params: Map<String, String>? = null

    /**
     * 构造函数
     */
    fun BaseModel(){//listener: BaseModelListener?
//        this.listener = listener
    }

    fun postJson(url: String, paramJson: JSONObject, callback: Callback){

        var okHttpClient: OkHttpClient = OkHttpClient()

//        var request = Request.Builder()
//            .url(url).post(paramJson)
    }

}