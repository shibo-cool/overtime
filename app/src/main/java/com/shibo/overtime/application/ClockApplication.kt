package com.shibo.overtime.application

import android.app.Application

class ClockApplication : Application() {

    companion object{

        var application: ClockApplication? = null

        fun getInstance(): ClockApplication{
            if(application == null){
                application = ClockApplication()
            }
            return application!!
        }
    }
}