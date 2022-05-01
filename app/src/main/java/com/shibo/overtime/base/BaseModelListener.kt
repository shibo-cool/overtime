package com.shibo.overtime.base

/**
 * 接口请求监听接口
 * @author shibo
 * @date 2022/4/26
 */
interface BaseModelListener<T, E> {

    /**
     * 请求成功
     */
    fun onSuccess(request: T, response: E)

    /**
     * 请求失败
     */
    fun onFailure(request: T, message: String)
}