package com.shibo.overtime.base

import okhttp3.OkHttpClient

/**
 * @author shibo
 * @date 2022/5/7
 *
 */
class OkHttpClient {

    companion object{

        private var okHttpClient: OkHttpClient? = null

        fun getInstance(): OkHttpClient? {
            if(okHttpClient == null){
                okHttpClient = OkHttpClient()
            }
            return okHttpClient
        }
    }
  }