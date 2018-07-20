package cn.wj.android.common.expanding

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.ColorInt
import cn.wj.android.common.utils.AppManager

/**
 * 根据资源 id 获取颜色值
 */
@ColorInt
fun Int.getColor(): Int {
    return this.getColor(AppManager.getApplication())
}

/**
 * 根据资源 id 获取颜色值
 */
@ColorInt
fun Int.getColor(context: Context): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        context.getColor(this)
    } else {
        @Suppress("DEPRECATION")
        context.resources.getColor(this)
    }
}

/**
 * 根据资源 id 获取字符串
 */
fun Int.getString(): String {
    return this.getString(AppManager.getApplication())
}

/**
 * 根据资源 id 获取字符串
 */
fun Int.getString(context: Context): String {
    return context.getString(this)
}

/**
 * 根据资源 id 获取 Drawable
 */
fun Int.getDrawable(): Drawable {
    return this.getDrawable(AppManager.getApplication())
}

/**
 * 根据资源 id 获取 Drawable
 */
fun Int.getDrawable(context: Context): Drawable {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        context.getDrawable(this)
    } else {
        @Suppress("DEPRECATION")
        context.resources.getDrawable(this)
    }
}