package com.shibo.overtime.tool

import android.content.Context
import android.content.SharedPreferences

/**
 * @author shibo
 * @date 2022/5/6
 * 共享对象
 */
class SharedPreferencesUtil {

  companion object{

    const val SHARED_PREFERENCE_NAME = "SharedPreferencesUtil"
    const val USER_ID = "userId"
    const val USER_TOKEN = "userToken"
  }

  private var sharedPreferences: SharedPreferences? = null

  constructor(context: Context){
    sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
  }

  /**
   * 获取共享对象
   */
  fun getUtil(): SharedPreferences?{
    return sharedPreferences
  }

  /**
   * 获取共享对象编辑器
   */
  fun getSharedPreferencesEditor(): SharedPreferences.Editor?{
    return sharedPreferences?.edit()
  }

  /**
   * 获取共享对象值
   * String类型
   */
  fun getString(key: String, defValue: String):String?{
    return sharedPreferences?.getString(key, defValue)
  }

  /**
   * 存储对象值
   * String类型
   */
  fun setValue(key: String, value: String){
    val editor = getSharedPreferencesEditor()
    editor?.putString(key, value)
    editor?.commit()
  }
}