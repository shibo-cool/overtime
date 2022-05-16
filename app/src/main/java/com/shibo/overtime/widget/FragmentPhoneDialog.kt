package com.shibo.overtime.widget

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.shibo.overtime.R
import java.io.File
import java.io.IOException

/**
 * @author shibo
 * @date 2022/5/15
 *
 */
class FragmentPhoneDialog: DialogFragment(), View.OnClickListener {

    companion object{

        @JvmStatic
        fun lessenUriImage(path: String?): Bitmap {
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            var bitmap = BitmapFactory.decodeFile(path, options) //此时返回 bm 为空
            options.inJustDecodeBounds = false //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
            var be = (options.outHeight / 320.toFloat()).toInt()
            if (be <= 0) be = 1
            options.inSampleSize = be //重新读入图片，注意此时已经把 options.inJustDecodeBounds 设回 false 了
            bitmap = BitmapFactory.decodeFile(path, options)
            val w = bitmap.width
            val h = bitmap.height
            println("$w $h") //after zoom
            return bitmap
        }
    }

    /**删除内容 */
    private var linear_del: LinearLayout? = null

    /**拍照 */
    private var btn_camera: Button? = null

    /**重从相册 */
    private var btn_album: Button? = null

    /**删除 */
    private var btn_del: Button? = null

    /**取消 */
    private var btn_cancel: Button? = null

    /** 裁剪图片code  */
    private val REQUEST_IMAGE_CROP = 2000

    /** 相册获得图片code  */
    private val REQUEST_IMAGE_CHOICE = 10

    /** 拍照获得图片code  */
    private val REQUEST_IMAGE_CAPTURE = 20

    /** 拍照获得图片Url  */
    private var mCamerUri: Uri? = null

    /** 头像Bitmap  */
    private var mHeadBitmap: Bitmap? = null

    /**返回成功结果处理 */
    private var resultSuccess: ResultSuccess? = null

    /**返回失败结果处理 */
    private var resultError: ResultError? = null

    /**删除动作 */
    private var delAction: DelAction? = null

    /**图片裁剪宽度 */
    private var mWidth = 300

    /**图片裁剪高度 */
    private var mHeight = 300

    /**是否显示删除按钮 */
    private var isShowDel = false

    var mView: View? = null

    private var fileUri: Uri? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
      mView = inflater?.inflate(R.layout.fragment_dialog_take_photo, null)
        initView()
        setListener()
        initData()
        return mView!!
    }

    fun initView() {
        btn_camera = view.findViewById(R.id.btn_camera)
        btn_album = view.findViewById(R.id.btn_album)
        btn_cancel = view.findViewById(R.id.btn_cancel)
        linear_del = view.findViewById(R.id.linear_del)
        btn_del = view.findViewById(R.id.btn_del)
    }

    fun initData() {
        if (isShowDel) {
            linear_del!!.visibility = View.VISIBLE
        } else {
            linear_del!!.visibility = View.GONE
        }
    }

    fun setListener() {
        btn_camera!!.setOnClickListener(this)
        btn_album!!.setOnClickListener(this)
        btn_cancel!!.setOnClickListener(this)
        btn_del!!.setOnClickListener(this)
    }

    fun setPhoneSize(width: Int, height: Int) {
        mWidth = width
        mHeight = height
    }

    fun isShowDel(isShowDel: Boolean){
        this.isShowDel = isShowDel
    }

    fun setResultSuccess(result: ResultSuccess?) {
        resultSuccess = result
    }

    fun setResultError(result: ResultError?) {
        resultError = result
    }

    fun setDelAction(delAction: DelAction?) {
        this.delAction = delAction
    }

    interface ResultSuccess {
        fun success(bitmap: Bitmap?)
    }

    interface ResultError {
        fun error(e: Exception?)
        fun successNull()
    }

    interface DelAction {
        fun del()
    }

    override fun onClick(p0: View?) {
        when (view) {
            btn_camera -> {
                //拍照
                doTakePhoto()
            }
            btn_album -> {
                //从相册里面
                doPickPhotoFromGallery()
            }
            btn_cancel -> {
                //取消
                dismiss()
            }
            btn_del -> {
                //删除
                del()
            }
        }
    }

    /**
     * 开启拍照功能
     */
    private fun doTakePhoto() {
        val captrueIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE, null)
        val picFile: File = getPicFile()
        if (picFile != null) {
            captrueIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(picFile))
            startActivityForResult(captrueIntent, REQUEST_IMAGE_CAPTURE)
            mCamerUri = Uri.fromFile(picFile)
        }
    }

    /**
     * 获得图片缓存文件
     */
    private fun getPicFile(): File {
        val photoFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
        if (!photoFile.exists()) {
            MyToast.showToast(activity, getString(R.string.pick_photo_sd_miss))
        }
        val file = File(photoFile.absolutePath + "/dcim" + System.currentTimeMillis() + ".jpg")
        if (!file.exists()) {
            try {
                file.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return file
    }

    /**
     * 开启从相册获得图片功能
     */
    private fun doPickPhotoFromGallery() {
        try {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*" // 设置文件类型
            startActivityForResult(intent, REQUEST_IMAGE_CHOICE) // 转到图库
        } catch (e: java.lang.Exception) {
            MyToast.showToast(activity, getString(R.string.pick_photo_err))
        }
    }

    /**
     * 删除
     */
    private fun del() {
        if (delAction != null) delAction!!.del() else dismiss()
    }

    fun getPath(uri: Uri?): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = activity.contentResolver.query(uri!!, projection, null, null, null)
        val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(column_index)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_IMAGE_CHOICE -> {
                var uri: Uri? = null
                if (data != null) {
                    uri = data.data
                }
                if (uri == null) {
                    return
                }
                try {
//                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                    val bitmap = lessenUriImage(getPath(uri))
                    if (bitmap.width < mWidth || bitmap.height < mHeight) {
                        MyToast.showToast(activity, getString(R.string.photo_too_small))
                        successNull()
                        return
                    }
                } catch (e: java.lang.Exception) {
                    error(e)
                    return
                }
                doZoomImage(uri, mWidth, mHeight, true)
            }
            REQUEST_IMAGE_CAPTURE -> {
                try {
                    val bitmap =
                        MediaStore.Images.Media.getBitmap(activity.contentResolver, mCamerUri)
                    if (bitmap.width < mWidth || bitmap.height < mHeight) {
                        successNull()
                        MyToast.showToast(activity, getString(R.string.photo_too_small))
                        return
                    }
                } catch (e: java.lang.Exception) {
                    error(e)
                    return
                }
                if(null != mCamerUri) {
                    doZoomImage(mCamerUri!!, mWidth, mHeight, false)
                }
            }
            REQUEST_IMAGE_CROP -> try {
                if (Build.VERSION.SDK_INT >= 23) {
                    try {
                        if(null != fileUri) {
                            mHeadBitmap = BitmapFactory.decodeStream(
                                activity.contentResolver.openInputStream(fileUri!!)
                            )
                        }
                        if (null != mHeadBitmap) {
                            success(mHeadBitmap)
                        }
                    } catch (e: java.lang.Exception) {
                        MyToast.showToast(activity, getString(R.string.pick_photo_data_null))
                        successNull()
                    }
                } else {
                    if (null == data || null == data.extras) {
                        successNull()
                        return
                    }
                    val extras = data.extras
                    if (null != mHeadBitmap) { // 内存中只保存一份头像的数据
                        mHeadBitmap?.recycle()
                    }
                    mHeadBitmap = extras!!.getParcelable("data")
                    if (null != mHeadBitmap) {
                        success(mHeadBitmap)
                    } else {
                        MyToast.showToast(activity, getString(R.string.pick_photo_data_null))
                        successNull()
                    }
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun successNull() {
        if (resultError != null) {
            resultError!!.successNull()
        } else {
            dismiss()
        }
    }

    private fun success(bitmap: Bitmap?) {
        if (resultSuccess != null) {
            resultSuccess!!.success(bitmap)
        } else {
            dismiss()
        }
    }

    /**
     * 开启图片裁剪功能
     */
    fun doZoomImage(uri: Uri, width: Int, height: Int, a: Boolean): Boolean {
        var vIdRet = true
        try {
            val intent = Intent("com.android.camera.action.CROP")
            if (Build.VERSION.SDK_INT >= 23) {
                var bmp: Bitmap? = null
                var str: String? = ""
                bmp = if (a == false) {
                    lessenUriImage(uri.path)
                } else {
                    lessenUriImage(getPath(uri))
                }
                str = MediaStore.Images.Media.insertImage(activity.contentResolver, bmp, "", "")
                fileUri = Uri.parse(str)
                intent.setDataAndType(fileUri, "image/*")
            } else {
                intent.setDataAndType(uri, "image/*")
            }

            // 设置裁剪
            intent.putExtra("crop", "true")
            intent.putExtra("outputX", width)
            intent.putExtra("outputY", height)
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", width)
            intent.putExtra("aspectY", height)
            intent.putExtra("return-data", true)
            startActivityForResult(intent, REQUEST_IMAGE_CROP)
        } catch (e: java.lang.Exception) {
            vIdRet = false
        }
        return vIdRet
    }
}