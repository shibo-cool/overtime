package com.shibo.overtime.base

import java.io.Serializable

open class BaseEntity: Serializable {

    /**
     * 请求返回状态码
     */
    var code: Int = 200

    /**
     * 请求返回消息
     */
    var message: String? = null

    /**
     * 请求的时间戳
     */
    var timestamp: String? = null
}