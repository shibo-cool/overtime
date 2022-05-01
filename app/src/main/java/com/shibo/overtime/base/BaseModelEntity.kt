package com.shibo.overtime.base

import java.io.Serializable

class BaseModelEntity: Serializable {

    /**
     * 请求状态返回码
     */
    var code: Int? = 0

    /**
     * 请求返回消息
     */
    var message: String? = null

    /**
     * 请求返回的时间戳
     */
    var timestamp: String? = null
}