package com.shibo.overtime.main.changename

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.shibo.overtime.R
import com.shibo.overtime.base.BaseActivity
import com.shibo.overtime.widget.MyToast

/**
 * @author shibo
 * @date 2022/5/15
 * 修改昵称
 */
class ChangeNameActivity: BaseActivity() {

    companion object{

        @JvmStatic
        fun start(context: Context){
            val intent = Intent(context, ChangeNameActivity::class.java)
            context.startActivity(intent)
        }
    }

    /**
     * 返回键
     */
    private var mBack: ImageView? = null

    /**
     * 确定键
     */
    private var mOk: TextView? = null

    /**
     * 昵称输入框
     */
    private var mEtNickName: EditText? = null

    /**
     * 清除图标
     */
    private var mClear: ImageView? = null

    override fun getContentView(): Int {
        return R.layout.activity_change_name
    }

    override fun initView() {
        mBack = findViewById(R.id.back)
        mOk = findViewById(R.id.ok)
        mEtNickName = findViewById(R.id.et_nick_name)
        mClear = findViewById(R.id.et_nick_name_clear)
    }

    override fun initData() {

    }

    override fun setListener() {
        mBack?.setOnClickListener{
            finish()
        }

        mClear?.setOnClickListener{
            mEtNickName?.setText("")
        }

        mOk?.setOnClickListener{
            val nickName: String = mEtNickName?.text.toString()
            if (TextUtils.isEmpty(nickName)) {
                MyToast.showToast(this, "昵称不能为空！")
                return@setOnClickListener
            }
            val intent = Intent()
            intent.putExtra("nick_name", nickName)
            setResult(RESULT_OK, intent)
            val v1: View = this.window.peekDecorView()
            closeSoftInput(this, v1)
            finish()
        }
    }

    private fun closeSoftInput(activity: Activity, view: View) {
        val imm = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}