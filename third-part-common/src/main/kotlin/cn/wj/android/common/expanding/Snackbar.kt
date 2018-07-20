package cn.wj.android.common.expanding

import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import cn.wj.android.common.widget.topsnackbar.SnackbarContentLayout
import cn.wj.android.common.widget.topsnackbar.TopSnackbar

/**
 * 获取内容布局
 * - 继承 LinearLayout
 */
fun TopSnackbar.getContentLayout(): SnackbarContentLayout {
    return (this.view as TopSnackbar.SnackBarLayout).getChildAt(0) as SnackbarContentLayout
}

/**
 * 获取动作按钮
 * - Button
 */
fun TopSnackbar.getActionView(): Button {
    return this.getContentLayout().actionView
}

/**
 * 设置按钮背景
 */
fun TopSnackbar.setActionBackgroundResource(@DrawableRes resID: Int): TopSnackbar {
    this.getActionView().setBackgroundResource(resID)
    return this
}

/**
 * 设置按钮文本
 */
fun TopSnackbar.setActionText(str: String): TopSnackbar {
    this.getActionView().visibility = View.VISIBLE
    this.getActionView().text = str
    return this
}

/**
 * 设置按钮文本
 */
fun TopSnackbar.setActionText(@StringRes strResID: Int): TopSnackbar {
    this.getActionView().visibility = View.VISIBLE
    this.getActionView().setText(strResID)
    return this
}

/**
 * 设置按钮文本大小
 */
fun TopSnackbar.setActionTextSize(size: Float): TopSnackbar {
    this.getActionView().textSize = size
    return this
}

/**
 * 设置按钮点击事件
 */
fun TopSnackbar.setActionClickListener(click: (View, TopSnackbar) -> Unit): TopSnackbar {
    this.getActionView().setOnClickListener {
        click(it, this)
    }
    return this
}

/**
 * 设置 Snackbar 背景
 */
fun TopSnackbar.setBackgroundResource(@DrawableRes resID: Int): TopSnackbar {
    this.view.setBackgroundResource(resID)
    return this
}

/**
 * 获取消息控件
 * - TextView
 */
fun TopSnackbar.getMessageView(): TextView {
    return this.getContentLayout().messageView
}

/**
 * 设置消息文本
 */
fun TopSnackbar.setMessageText(str: String): TopSnackbar {
    this.getMessageView().text = str
    return this
}

/**
 * 设置消息文本
 */
fun TopSnackbar.setMessageText(@StringRes strResID: Int): TopSnackbar {
    this.getMessageView().setText(strResID)
    return this
}

/**
 * 设置消息文本颜色
 */
fun TopSnackbar.setMessageTextColor(@ColorInt color: Int): TopSnackbar {
    this.getMessageView().setTextColor(color)
    return this
}

/**
 * 设置消息文本大小
 */
fun TopSnackbar.setMessageTextSize(size: Float): TopSnackbar {
    this.getMessageView().textSize = size
    return this
}

/**
 * 添加 View 到指定位置
 */
fun TopSnackbar.addView(v: View, index: Int, params: LinearLayout.LayoutParams): TopSnackbar {
    this.getContentLayout().addView(v, index, params)
    return this
}

/**
 * 添加 View 到指定位置 且设置点击事件
 */
fun TopSnackbar.addView(v: View, index: Int, params: LinearLayout.LayoutParams, click: (View, TopSnackbar) -> Unit): TopSnackbar {
    v.setOnClickListener {
        click(it, this)
    }
    this.getContentLayout().addView(v, index, params)
    return this
}

fun Snackbar.getContentLayout(): android.support.design.internal.SnackbarContentLayout {
    return (this.view as Snackbar.SnackbarLayout).getChildAt(0) as android.support.design.internal.SnackbarContentLayout
}

fun Snackbar.getActionView(): Button {
    return this.view.findViewById(android.support.design.R.id.snackbar_action)
}

fun Snackbar.getMessageView(): TextView {
    return this.view.findViewById(android.support.design.R.id.snackbar_text)
}


