package cn.wj.android.common.databinding

import android.databinding.BindingAdapter
import android.graphics.Color
import android.support.v4.view.ViewCompat
import android.view.MotionEvent
import android.view.View
import cn.wj.android.common.expanding.dip2px
import cn.wj.android.common.expanding.orFalse

/**
 * ViewPager DataBinding 适配器
 */
class ViewBindingAdapter {

    companion object {

        /**
         * 设置点击事件
         *
         * @param v [View] 对象
         * @param click 点击回调
         */
        @JvmStatic
        @BindingAdapter("android:bind_onClick")
        fun setViewOnClick(v: View, click: ((View) -> Unit)?) {
            v.setOnClickListener(click)
        }

        /**
         * 设置点击事件
         *
         * @param v [View] 对象
         * @param click 点击回调
         */
        @JvmStatic
        @BindingAdapter("android:bind_onClick")
        fun setViewOnClick(v: View, click: (() -> Unit)?) {
            v.setOnClickListener {
                click?.invoke()
            }
        }

        /**
         * 设置点击事件
         *
         * @param v [View] 对象
         * @param click 点击回调
         */
        @JvmStatic
        @BindingAdapter("android:bind_onClick", "android:bind_onClick_item")
        fun <T> setViewOnClick(v: View, click: ((View, T) -> Unit)?, item: T) {
            v.setOnClickListener { view -> click?.invoke(view, item) }
        }

        /**
         * 设置点击事件
         *
         * @param v [View] 对象
         * @param click 点击回调
         */
        @JvmStatic
        @BindingAdapter("android:bind_onClick", "android:bind_onClick_item")
        fun <T> setViewOnClick(v: View, click: ((T) -> Unit)?, item: T) {
            v.setOnClickListener { click?.invoke(item) }
        }

        /**
         * 设置点击事件
         *
         * @param v [View] 对象
         * @param listener 点击回调
         */
        @JvmStatic
        @BindingAdapter("android:bind_onClick")
        fun setViewOnClick(v: View, listener: View.OnClickListener?) {
            v.setOnClickListener(listener)
        }

        /**
         * 设置长点击事件
         *
         * @param v [View] 对象
         * @param click 点击回调
         */
        @JvmStatic
        @BindingAdapter("android:bind_onLongClick")
        fun setViewOnLongClick(v: View, click: ((View) -> Boolean)?) {
            v.setOnLongClickListener(click)
        }

        /**
         * 设置长点击事件
         *
         * @param v [View] 对象
         * @param click 点击回调
         */
        @JvmStatic
        @BindingAdapter("android:bind_onLongClick")
        fun setViewOnLongClick(v: View, click: (() -> Boolean)?) {
            v.setOnLongClickListener { click?.invoke().orFalse() }
        }

        /**
         * 设置长点击事件
         *
         * @param v [View] 对象
         * @param click 点击回调
         */
        @JvmStatic
        @BindingAdapter("android:bind_onLongClick", "android:bind_onLongClick_item")
        fun <T> setViewOnLongClick(v: View, click: ((View, T) -> Boolean)?, item: T) {
            v.setOnLongClickListener { click?.invoke(it, item).orFalse() }
        }

        /**
         * 设置长点击事件
         *
         * @param v [View] 对象
         * @param click 点击回调
         */
        @JvmStatic
        @BindingAdapter("android:bind_onLongClick", "android:bind_onLongClick_item")
        fun <T> setViewOnLongClick(v: View, click: ((T) -> Boolean)?, item: T) {
            v.setOnLongClickListener { click?.invoke(item).orFalse() }
        }

        /**
         * 设置 View 显示
         *
         * @param v [View] 对象
         * @param visibility 显示状态
         */
        @JvmStatic
        @BindingAdapter("android:bind_visibility")
        fun setViewVisibility(v: View, visibility: Int) {
            v.visibility = visibility
        }

        /**
         * 设置 View 显示
         *
         * @param v [View] 对象
         * @param show 是否显示
         */
        @JvmStatic
        @BindingAdapter("android:bind_visibility")
        fun setViewVisibility(v: View, show: Boolean) {
            v.visibility = if (show) View.VISIBLE else View.GONE
        }

        /**
         * 设置 View 选中
         *
         * @param v [View] 对象
         * @param selected 是否选中
         */
        @JvmStatic
        @BindingAdapter("android:bind_selected")
        fun setViewSelected(v: View, selected: Boolean) {
            v.isSelected = selected
        }

        /**
         * 设置 View 布局监听
         *
         * @param v [View] 对象
         * @param onGlobal 回调
         */
        @JvmStatic
        @BindingAdapter("android:bind_onGlobal")
        fun setViewOnGlobal(v: View, onGlobal: ((View) -> Unit)?) {
            v.viewTreeObserver.addOnGlobalLayoutListener {
                onGlobal?.invoke(v)
            }
        }

        /**
         * 设置 View 布局监听
         *
         * @param v [View] 对象
         * @param onGlobal 回调
         */
        @JvmStatic
        @BindingAdapter("android:bind_onGlobal")
        fun setViewOnGlobal(v: View, onGlobal: (() -> Unit)?) {
            v.viewTreeObserver.addOnGlobalLayoutListener {
                onGlobal?.invoke()
            }
        }

        /**
         * 设置 View 触摸监听
         *
         * @param v [View] 对象
         * @param onTouch 回调
         */
        @JvmStatic
        @BindingAdapter("android:bind_onTouch")
        fun setViewOnTouch(v: View, onTouch: ((View, MotionEvent) -> Boolean)?) {
            v.setOnTouchListener(onTouch)
        }

        /**
         * 设置 View 触摸监听
         *
         * @param v [View] 对象
         * @param onTouch 回调
         */
        @JvmStatic
        @BindingAdapter("android:bind_onTouch")
        fun setViewOnTouch(v: View, onTouch: ((MotionEvent) -> Boolean)?) {
            v.setOnTouchListener { _, ev ->
                onTouch?.invoke(ev).orFalse()
            }
        }

        /**
         * 设置 View 触摸监听
         *
         * @param v [View] 对象
         * @param onTouch 回调
         */
        @JvmStatic
        @BindingAdapter("android:bind_onTouch")
        fun setViewOnTouch(v: View, onTouch: (() -> Boolean)?) {
            v.setOnTouchListener { _, _ ->
                onTouch?.invoke().orFalse()
            }
        }

        /**
         * 根据字符串颜色值设置背景色
         *
         * @param v [View] 对象
         * @param color 颜色值，如：**#FFFFFF**
         */
        @JvmStatic
        @BindingAdapter("android:bind_background")
        fun setBackground(v: View, color: String?) {
            if (!color.isNullOrEmpty()) {
                v.setBackgroundColor(Color.parseColor(color))
            }
        }

        /**
         * 根据资源 id 设置背景
         *
         * @param v [View] 对象
         * @param resID 背景资源 id
         */
        @JvmStatic
        @BindingAdapter("android:bind_background")
        fun setBackgroundRes(v: View, resID: Int) {
            if (0 == resID) {
                v.background = null
            } else {
                v.setBackgroundResource(resID)
            }
        }

        /**
         * 设置 View 海拔高度
         * - 仅 API >= LOLLIPOP 有效
         *
         * @param elevation 高度 单位:dp
         */
        @JvmStatic
        @BindingAdapter("android:bind_elevation")
        fun setElevation(v: View, elevation: Float) {
            ViewCompat.setElevation(v, elevation.dip2px(v.context))
        }
    }
}