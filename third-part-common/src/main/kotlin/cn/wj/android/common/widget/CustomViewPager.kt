package cn.wj.android.common.widget

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import cn.wj.android.common.R

/**
 * 自定义 ViewPager，可配置能否滑动
 */
class CustomViewPager @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null)
    : ViewPager(context, attrs) {

    /**
     * 是否允许滑动
     */
    var mScrollable = false

    init {
        val types = context.obtainStyledAttributes(attrs, R.styleable.CustomViewPager)
        mScrollable = types.getBoolean(R.styleable.CustomViewPager_scrollable, true)
        types.recycle()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return mScrollable && super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return mScrollable && super.onInterceptTouchEvent(ev)
    }
}