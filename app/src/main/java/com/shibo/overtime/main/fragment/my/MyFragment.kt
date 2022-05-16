package com.shibo.overtime.main.fragment.my

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.support.v4.app.FragmentManager
import android.util.Base64
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.shibo.overtime.R
import com.shibo.overtime.base.BaseFragment
import com.shibo.overtime.base.ShowLoadingListener
import com.shibo.overtime.login.model.entity.LoginEntity
import com.shibo.overtime.main.MainActivity
import com.shibo.overtime.main.approvalNote.ApprovalNoteActivity
import com.shibo.overtime.main.changename.ChangeNameActivity
import com.shibo.overtime.main.fragment.my.presenter.MyPresenter
import com.shibo.overtime.main.fragment.my.view.MyView
import com.shibo.overtime.main.setpassword.SetPasswordActivity
import com.shibo.overtime.widget.ExitDialog
import com.shibo.overtime.widget.FragmentPhoneDialog
import com.shibo.overtime.widget.MyToast
import com.shibo.overtime.widget.SexDialog
import java.io.ByteArrayOutputStream

class MyFragment: BaseFragment, View.OnClickListener, MyView {

    companion object{
        const val REQUEST_CODE = 11
    }

    /**头像 */
    private var mHead: ImageView? = null

    /**修改昵称 */
    private var nRltNickName: RelativeLayout? = null

    /**部门 */
    private var mTvDepartment: TextView? = null

    /**性别 */
    private var mTvSex: TextView? = null

    /**昵称 */
    private var mTvNickName: TextView? = null

    /**账号 */
    private var mTvAccount: TextView? = null

    /**修改昵称 */
    private var nRltSex: RelativeLayout? = null

    /**设置密码 */
    private var nRltSetPwd: RelativeLayout? = null

    /**查看审批记录 */
    private var nRltApprovalRecord: RelativeLayout? = null

    /**退出登录 */
    private var mTvExitLogin: TextView? = null

    /**头像 */
    private var mBitmap: Bitmap? = null

    private var mPresenter: MyPresenter? = null

    constructor(listener: ShowLoadingListener):super(listener)

    override fun getContentView(): Int {
        return R.layout.fragment_my
    }

    override fun initView(view: View) {
        mHead = view.findViewById(R.id.iv_head)
        nRltNickName = view.findViewById(R.id.rlt_nick_name)
        nRltSex = view.findViewById(R.id.rlt_sex)
        nRltSetPwd = view.findViewById(R.id.rlt_set_pwd)
        nRltApprovalRecord = view.findViewById(R.id.rlt_approval_record)
        mTvExitLogin = view.findViewById(R.id.tv_exit_login)
        mTvDepartment = view.findViewById(R.id.tv_department)
        mTvNickName = view.findViewById(R.id.tv_nick_name)
        mTvAccount = view.findViewById(R.id.tv_account)
        mTvSex = view.findViewById(R.id.tv_sex)
    }

    override fun initData() {
        mPresenter = MyPresenter(activity as Context, this)
        mPresenter?.myInfo()

    }

    override fun setListener() {
        mHead?.setOnClickListener(this)
        nRltNickName?.setOnClickListener(this)
        nRltSex?.setOnClickListener(this)
        nRltSetPwd?.setOnClickListener(this)
        nRltApprovalRecord?.setOnClickListener(this)
        mTvExitLogin?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0){
            mHead -> {
                selectPhone(mHead!!)
            }
            nRltNickName -> {
                val intentNick = Intent(activity, ChangeNameActivity::class.java)
                intentNick.putExtra("nick_name", mTvNickName?.text.toString())
                startActivityForResult(intentNick, REQUEST_CODE)
            }
            nRltSex -> {
                val sexDialog = SexDialog()
                sexDialog.setListener(object : SexDialog.Listener {
                    override fun sexMale() {
                        sexDialog.dismiss()
                        mTvSex?.text = getString(R.string.sex_male)
                        mTvSex?.tag = 1
                        edit()
                    }

                    override fun sexFemale() {
                        sexDialog.dismiss()
                        mTvSex?.text = getString(R.string.sex_female)
                        mTvSex?.tag = 2
                        edit()
                    }

                    override fun cancel() {
                        sexDialog.dismiss()
                    }
                })
                sexDialog.show(requireFragmentManager(), "Sex")
            }
            nRltSetPwd -> {
                val intentPwd = Intent(activity, SetPasswordActivity::class.java)
                startActivity(intentPwd)
            }
            nRltApprovalRecord -> {
                ApprovalNoteActivity.start(activity as Context)
            }
            mTvExitLogin -> {
                val exitDialog = ExitDialog()
                exitDialog.show(requireFragmentManager(), "EXIT")
            }
        }
    }

    /**
     * 从相册中获取图片
     */
    private fun selectPhone(iv: ImageView){
        val dialog = FragmentPhoneDialog()
        dialog.setPhoneSize(300,300)
        dialog.isShowDel(false)
        dialog.setResultSuccess(object: FragmentPhoneDialog.ResultSuccess{
            override fun success(bitmap: Bitmap?) {
                if (null != bitmap) {
                    // 修改用户头像
                    iv.setImageBitmap(bitmap)
                    mBitmap = bitmap
                    edit()
                }
                dialog.dismiss()
            }

        })
    }

    fun edit() {
        val nickname: String = mTvNickName?.text.toString()
        val gender: Int = if (mTvSex?.tag == null) {
            1
        } else {
            mTvSex?.tag as Int
        }
        var photo = ""
        if (mBitmap != null) photo = convertIconToString(mBitmap!!)
        mPresenter?.edit(photo, nickname, gender)
    }

    /**
     * 图片转成string
     * @param bitmap
     * @return
     */
    private fun convertIconToString(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos)
        val appicon = baos.toByteArray() // 转为byte数组
        return Base64.encodeToString(appicon, Base64.DEFAULT)
    }

    private fun setData(entity: LoginEntity) {
//        Glide.with(activity as Context).load(entity.data?.photo).into(mHead)
        mTvDepartment?.text = entity.data?.realname
        mTvAccount?.text = entity.data?.id
        if ("0".equals(entity.data?.gender, false)) {
            mTvSex?.text = getString(R.string.confidentiality)
        }
        when (entity.data?.gender) {
            "0" -> {
                mTvSex?.text = getString(R.string.confidentiality)
                mTvSex?.tag = 0
            }
            "1" -> {
                mTvSex?.text = getString(R.string.sex_male)
                mTvSex?.tag = 1
            }
            else -> {
                mTvSex?.text = getString(R.string.sex_female)
                mTvSex?.tag = 2
            }
        }
        mTvNickName?.text = entity.data?.nickname
    }

    override fun infoSuccess(entity: LoginEntity) {
        setData(entity)
    }

    override fun infoFailure(message: String) {
        MyToast.showToast(activity as Context, message)
    }

    override fun editSuccess(response: LoginEntity) {
        setData(response)
    }

    override fun editFailure(message: String) {
        MyToast.showToast(activity as Context, message)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                if (data == null) {
                } else {
                    mTvNickName?.text = data.getStringExtra("nick_name")
                    edit()
                }
            }
        }
    }
}