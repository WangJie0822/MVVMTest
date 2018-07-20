package cn.wj.android.common.utils

import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View
import cn.wj.android.common.expanding.getColor
import cn.wj.android.common.widget.topsnackbar.BaseTransientBottomBar
import cn.wj.android.common.widget.topsnackbar.TopSnackbar

/**
 * Toast 工具类
 */
object SnackbarUtil {

    /**
     * 弹出 Snackbar
     *
     * @param str 字符串
     * @param color 颜色值
     * @param top 标记 - 是否从顶部弹出 - 默认 false
     */
    fun show(v: View, str: String, @ColorInt color: Int, top: Boolean = false) {
        if (top) {
            val snackbar = TopSnackbar.make(v, str, BaseTransientBottomBar.LENGTH_SHORT)
            snackbar.view.setBackgroundColor(color)
            snackbar.show()
        } else {
            val snackbar = Snackbar.make(v, str, BaseTransientBottomBar.LENGTH_SHORT)
            snackbar.view.setBackgroundColor(color)
            snackbar.show()
        }
    }

    /**
     * 弹出 Snackbar
     *
     * @param strResID 字符串资源 id
     * @param colorResID 颜色资源 id
     * @param top 标记 - 是否从顶部弹出 - 默认 false
     */
    fun show(v: View, @StringRes strResID: Int, @ColorRes colorResID: Int, top: Boolean = false) {
        if (top) {
            val snackbar = TopSnackbar.make(v, strResID, BaseTransientBottomBar.LENGTH_SHORT)
            snackbar.view.setBackgroundColor(colorResID.getColor())
            snackbar.show()
        } else {
            val snackbar = Snackbar.make(v, strResID, BaseTransientBottomBar.LENGTH_SHORT)
            snackbar.view.setBackgroundColor(colorResID.getColor())
            snackbar.show()
        }
    }
}