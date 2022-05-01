package com.shibo.overtime.base

interface BaseListener : BaseModelListener<Any, Any> {

    override fun onSuccess(request: Any, response: Any)

    override fun onFailure(request: Any, message: String)
}