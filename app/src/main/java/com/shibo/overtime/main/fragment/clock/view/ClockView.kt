package com.shibo.overtime.main.fragment.clock.view

interface ClockView {

    fun clockSuccess(response: ClockModelEntity)

    fun clockFailure(message: String)
}