package cn.wj.android.common.utils

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.ActivityManager
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.StatFs
import android.provider.MediaStore
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import android.view.Gravity
import android.view.ViewConfiguration
import android.widget.Toast
import java.io.*
import java.util.*

/**
 * 应用相关工具类
 */
@Suppress("unused")
object AppUtil {

    /** 手机系统语言 */
    val systemLanguage: String
        get() = Locale.getDefault().language

    /** 当前系统上的语言列表(Locale列表) */
    val systemLanguageList: Array<Locale>
        get() = Locale.getAvailableLocales()

    /** 当前手机系统版本号 */
    val systemVersion: String
        get() = android.os.Build.VERSION.RELEASE

    /** 手机型号 */
    val systemModel: String
        get() = android.os.Build.MODEL

    /** 获取手机厂商 */
    val deviceBrand: String
        get() = android.os.Build.BRAND

    /**
     * 获取手机 IMEI
     *
     * @return IMEI
     */
    @SuppressLint("HardwareIds")
    fun getIMEI(): String {
        var imei: String?
        val context = AppManager.getApplication()
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        @Suppress("DEPRECATION")
        imei = telephonyManager.deviceId

        if (null == imei) {
            imei = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            if ("9774d56d682e549c" == imei) {
                // 某些主流设备 android_id是固定的
                imei = ""
            }
        }
        if (null == imei) {
            imei = ""
        }
        return imei
    }

    /**
     * 得到外部存储总空间(MB)
     */
    fun getTotalExternalMemorySize(): Long {
        return if (hasSdcard()) {
            val path = Environment.getExternalStorageDirectory()
            val stat = StatFs(path.path)
            val blockSize = stat.blockSizeLong
            val totalBlocks = stat.blockCountLong
            totalBlocks * blockSize
        } else {
            -1
        }
    }

    /**
     * 得到外部存储剩余空间(MB)
     */
    fun getAvailableExternalMemorySize(): Long {
        return if (hasSdcard()) {
            val path = Environment.getExternalStorageDirectory()
            val stat = StatFs(path.path)
            val blockSize = stat.blockSizeLong
            val availableBlocks = stat.availableBlocksLong
            availableBlocks * blockSize
        } else {
            -1
        }
    }

    /**
     * 将输入流写入文件
     */
    fun isToFile(`in`: InputStream, file: File): Boolean {

        var bos: BufferedOutputStream? = null
        var bis: BufferedInputStream? = null

        return try {
            // 打开一个已存在文件的输出流
            bos = BufferedOutputStream(FileOutputStream(file))
            bis = BufferedInputStream(`in`)

            // 将输入流is写入文件输出流fos中
            val bytes = ByteArray(1024)
            var len: Int

            while (true) {
                len = bis.read(bytes, 0, bytes.size)
                if (len != -1) {
                    bos.write(bytes, 0, len)
                } else {
                    break
                }
            }
            true
        } catch (e: Exception) {
            Log.e("ERROR", "FILE_ERROR")
            false
        } finally {
            //关闭输入流等（略）
            if (bos != null) {
                try {
                    bos.close()
                } catch (e: IOException) {
                    Log.e("ERROR", "FILE_ERROR")
                }
            }
            if (bis != null) {
                try {
                    bis.close()
                } catch (e: IOException) {
                    Log.e("ERROR", "FILE_ERROR")
                }
            }
        }
    }

    /**
     * 判断是否插入SD卡，SD卡是否可用
     */
    fun hasSdcard() = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED && Environment.getExternalStorageDirectory().canWrite()

    /**
     * 获取屏幕宽度
     *
     * @return 屏幕宽度，单位：像素
     */
    fun getScreenWidth() = getScreenWidth(AppManager.getApplication())

    /**
     * 获取屏幕宽度
     *
     * @return 屏幕宽度，单位：像素
     */
    fun getScreenWidth(context: Context) = context.resources.displayMetrics.widthPixels

    /**
     * 获取虚拟按键的高度
     */
    fun getNavigationBarHeight(): Int {
        val context = AppManager.peekActivity()
        var result = 0
        if (hasNavBar(context)) {
            val res = context.resources
            val resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android")
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId)
            }
        }
        return result
    }

    /**
     * 检查是否存在虚拟按键栏
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private fun hasNavBar(context: Context): Boolean {
        val res = context.resources
        val resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android")
        return if (resourceId != 0) {
            var hasNav = res.getBoolean(resourceId)
            // checked override flag
            val sNavBarOverride = getNavBarOverride()
            if ("1" == sNavBarOverride) {
                hasNav = false
            } else if ("0" == sNavBarOverride) {
                hasNav = true
            }
            hasNav
        } else { // fallback
            !ViewConfiguration.get(context).hasPermanentMenuKey()
        }
    }

    /**
     * 判断虚拟按键栏是否重写
     */
    @SuppressLint("PrivateApi")
    private fun getNavBarOverride(): String? {
        var sNavBarOverride: String? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                val c = Class.forName("android.os.SystemProperties")
                val m = c.getDeclaredMethod("get", String::class.java)
                m.isAccessible = true
                sNavBarOverride = m.invoke(null, "qemu.hw.mainkeys") as String
            } catch (e: Throwable) {
            }

        }
        return sNavBarOverride
    }

    /**
     * 获取包名
     */
    fun getAppName(pID: Int, context: Context): String? {
        var processName: String? = null
        val am = context.getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val l = am.runningAppProcesses
        val i = l.iterator()
        while (i.hasNext()) {
            val info = i.next() as ActivityManager.RunningAppProcessInfo
            try {
                if (info.pid == pID) {
                    processName = info.processName
                    return processName
                }
            } catch (e: Exception) {
                Log.d("Process", "Error>> :" + e.toString())
            }

        }
        return processName
    }

    /**
     * 通过相册图片 Uri 获取图片路径
     */
    fun getImgPathByUri(imgUri: Uri): String {
        val activity = AppManager.peekActivity()
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = activity.contentResolver.query(imgUri, filePathColumn, null, null, null)
        if (cursor != null) {
            cursor.moveToFirst()
            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
            val picturePath = cursor.getString(columnIndex)
            cursor.close()

            if (picturePath == null || picturePath == "null") {
                val toast = Toast.makeText(activity, "找不到图片", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
                return ""
            }
            return picturePath
        } else {
            val file = File(imgUri.path)
            if (!file.exists()) {
                val toast = Toast.makeText(activity, "找不到图片", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
                return ""

            }
            return file.absolutePath
        }
    }
}
