package com.shibo.overtime.base

/**
 * 接口请求监听接口
 * @author shibo
 * @date 2022/4/26
 */
interface BaseModelListener<E:BaseEntity> {

    /**
     * 请求成功
     */
    fun onSuccess(response: E)

    /**
     * 请求失败
     */
    fun onFailure(message: String)
}