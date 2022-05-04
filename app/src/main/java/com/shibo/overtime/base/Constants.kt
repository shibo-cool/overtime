package com.shibo.overtime.base

class Constants {

    companion object{

        const val IP_HOST = "http://192.168.148.165:8088/"

        const val URL_LOGIN = "Interface/login"

        const val URL_INFO = "Interface/information"

        const val URL_EDIT = "Interface/edit"

        const val URL_CHAGNE_PWD = "Interface/changePassword"

        const val URL_SUB_CLOCK = "Interface/addRecord"

        const val URL_TOTAL = "Interface/getTotal"

        const val URL_RECORD = "Interface/disp"

        const val URL_ADD_RECORD = "Interface/addRecord"

        const val URL_STATUS = "Interface/getStatus"

        const val URL_APPROVAL = "Interface/approveList"

        const val URL_APPROVAL_PASS = "Interface/approvePass"

        fun SERVER_ADDRESS(): String? {
            return IP_HOST
        }
    }
}