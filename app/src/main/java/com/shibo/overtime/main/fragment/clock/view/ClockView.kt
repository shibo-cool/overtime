package com.shibo.overtime.main.fragment.clock.view

import com.shibo.overtime.main.fragment.clock.model.ClockModel
import com.shibo.overtime.main.fragment.clock.model.entity.ClockModelEntity

interface ClockView {

    fun clockSuccess(request: ClockModel, response: ClockModelEntity)

    fun clockFailure(request: ClockModel, message: String)
}