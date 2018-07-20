package cn.wj.android.common.databinding

import android.databinding.BindingAdapter
import android.support.v4.widget.SwipeRefreshLayout

/**
 * ViewPager DataBinding 适配器
 */
class SwipeRefreshBindingAdapter {

    companion object {

        /**
         * 设置刷新事件
         *
         * @param srl [SwipeRefreshLayout] 对象
         * @param refresh 刷新事件
         */
        @JvmStatic
        @BindingAdapter("android:bind_srl_onRefresh")
        fun setSwipeRefreshLayoutRefreshListener(srl: SwipeRefreshLayout, refresh: () -> Unit) {
            srl.setOnRefreshListener {
                refresh()
            }
        }

        /**
         * 设置刷新进度条颜色
         *
         * @param srl [SwipeRefreshLayout] 对象
         * @param color 颜色值
         */
        @JvmStatic
        @BindingAdapter("android:bind_srl_schemeColors")
        fun setSwipeRefreshLayoutSchemeColors(srl: SwipeRefreshLayout, color: Int) {
            srl.setColorSchemeColors(color)
        }

        /**
         * 设置刷新状态
         *
         * @param srl [SwipeRefreshLayout] 对象
         * @param refreshing 是否刷新
         */
        @JvmStatic
        @BindingAdapter("android:bind_srl_refreshing")
        fun setSwipeRefreshLayoutRefreshing(srl: SwipeRefreshLayout, refreshing: Boolean) {
            srl.isRefreshing = refreshing
        }
    }
}