package com.shibo.overtime.main.fragment.clock.view

import com.shibo.overtime.main.fragment.clock.model.entity.ClockStatusEntity

interface ClockView {

    fun clockSuccess(response: ClockStatusEntity)

    fun clockFailure(message: String)
}