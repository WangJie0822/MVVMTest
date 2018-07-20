package cn.wj.android.common.databinding

import android.databinding.BindingAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager

/**
 * ViewPager DataBinding 适配器
 */
class ViewPagerBindingAdapter {

    companion object {

        /**
         * 设置 ViewPager 页面切换监听
         *
         * @param vp [ViewPager] 对象
         * @param scrolled onPageScrolled 回调
         * @param selected onPageSelected 回调
         * @param changed onPageScrollStateChanged 回调
         */
        @JvmStatic
        @BindingAdapter("android:bind_vp_change_scrolled", "android:bind_vp_change_selected", "android:bind_vp_change_changed", requireAll = false)
        fun setViewPagerPageChangeListener(vp: ViewPager, scrolled: ((Int, Float, Int) -> Unit)?, selected: ((Int) -> Unit)?, changed: ((Int) -> Unit)?) {
            vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {
                    changed?.invoke(state)
                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                    scrolled?.invoke(position, positionOffset, positionOffsetPixels)
                }

                override fun onPageSelected(position: Int) {
                    selected?.invoke(position)
                }
            })
        }

        /**
         * 设置 ViewPager 预加载页数
         *
         * @param vp [ViewPager] 对象
         * @param offscreenPageLimit 预加载页数
         */
        @JvmStatic
        @BindingAdapter("android:bind_vp_offscreenPageLimit")
        fun setViewPagerOffscreenPageLimit(vp: ViewPager, offscreenPageLimit: Int) {
            vp.offscreenPageLimit = offscreenPageLimit
        }

        /**
         * 设置 ViewPager 位置
         *
         * @param vp [ViewPager] 对象
         * @param currentItem 位置
         * @param smoothScroll 是否平滑滚动
         */
        @JvmStatic
        @BindingAdapter("android:bind_vp_currentItem", "android:bind_vp_smoothScroll", requireAll = false)
        fun setViewPagerCurrentItem(vp: ViewPager, currentItem: Int, smoothScroll: Boolean = false) {
            vp.setCurrentItem(currentItem, smoothScroll)
        }

        /**
         * 设置 ViewPager 适配器
         *
         * @param vp [ViewPager] 对象
         * @param adapter 适配器
         */
        @JvmStatic
        @BindingAdapter("android:bind_vp_adapter")
        fun setViewPagerAdapter(vp: ViewPager, adapter: PagerAdapter?) {
            vp.adapter = adapter
        }
    }
}