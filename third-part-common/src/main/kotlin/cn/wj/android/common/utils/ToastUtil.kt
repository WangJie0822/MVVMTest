package cn.wj.android.common.utils

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast

/**
 * Toast 工具类
 */
@Suppress("unused")
object ToastUtil {

    /** 单例 Toast 对象 */
    private val mToast by lazy {
        return@lazy Toast.makeText(AppManager.getApplication(), "", Toast.LENGTH_SHORT)
    }

    /**
     * 弹 Toast 单例，只显示最后一个
     *
     * @param strResID 字符串资源 id
     */
    fun show(@StringRes strResID: Int) {
        mToast.duration = Toast.LENGTH_SHORT
        mToast.setText(strResID)
        mToast.show()
    }

    /**
     * 弹 Toast 单例，只显示最后一个
     *
     * @param str 字符串
     */
    fun show(str: String?) {
        mToast.duration = Toast.LENGTH_SHORT
        if (str.isNullOrEmpty()) {
            mToast.cancel()
        } else {
            mToast.setText(str)
            mToast.show()
        }
    }

    /**
     * 弹 Toast 长时 单例，只显示最后一个
     *
     * @param str 字符串
     */
    fun showLong(str: String?) {
        mToast.duration = Toast.LENGTH_LONG
        if (str.isNullOrEmpty()) {
            mToast.cancel()
        } else {
            mToast.setText(str)
            mToast.show()
        }
    }

    /**
     * 弹 Toast 长时 单例，只显示最后一个
     *
     *  @param strResID 字符串资源 id
     */
    fun showLong(@StringRes strResID: Int) {
        mToast.duration = Toast.LENGTH_LONG
        mToast.setText(strResID)
        mToast.show()
    }

    /**
     * 弹 Toast
     *
     * @param strResID 字符串资源 id
     */
    fun showNew(@StringRes strResID: Int) {
        showNew(AppManager.getApplication().getString(strResID))
    }

    /**
     * 弹 Toast
     *
     * @param str 字符串
     */
    fun showNew(str: String?) {
        str?.let {
            Toast.makeText(AppManager.getApplication(), it, Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 弹 Toast 长时
     *
     * @param str 字符串
     */
    fun showNewLong(str: String?) {
        str?.let {
            Toast.makeText(AppManager.getApplication(), it, Toast.LENGTH_LONG).show()
        }
    }

    /**
     * 弹 Toast 长时
     *
     *  @param strResID 字符串资源 id
     */
    fun showNewLong(@StringRes strResID: Int) {
        showLong(AppManager.getApplication().getString(strResID))
    }

    /**
     * 弹 Toast
     *
     * @param context Context 对象
     * @param strResID 字符串资源 id
     */
    fun show(context: Context, @StringRes strResID: Int) {
        Toast.makeText(context, strResID, Toast.LENGTH_SHORT).show()
    }

    /**
     * 弹 Toast
     *
     * @param context Context 对象
     * @param str 字符串
     */
    fun show(context: Context, str: String?) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
    }

    /**
     * 弹 Toast 长时
     *
     * @param context Context 对象
     * @param str 字符串
     */
    fun showLong(context: Context, str: String?) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show()
    }

    /**
     * 弹 Toast 长时
     *
     * @param context Context 对象
     *  @param strResID 字符串资源 id
     */
    fun showLong(context: Context, @StringRes strResID: Int) {
        Toast.makeText(context, strResID, Toast.LENGTH_LONG).show()
    }
}