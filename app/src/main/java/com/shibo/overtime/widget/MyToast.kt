package com.shibo.overtime.widget

import android.content.Context
import android.os.Looper
import android.widget.Toast

/**
 * @author shibo
 * @date 2022/5/15
 *
 */
class MyToast {

    companion object {

        fun showToast(context: Context, message: String){

          Looper.prepare()
          Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
          Looper.loop()
        }
    }
}