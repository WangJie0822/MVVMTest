package cn.wj.android.common.databinding

import android.databinding.BindingAdapter
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import cn.wj.android.common.widget.recyclerview.FlowLayoutManager

/**
 * ViewPager DataBinding 适配器
 */
class RecyclerViewBindingAdapter {

    companion object {

        /**
         * 设置是否使用自身滑动管理
         *
         * @param rv [RecyclerView] 对象
         * @param isNestedScrollingEnabled NestedScrollView 中是否使用自身滑动管理
         */
        @JvmStatic
        @BindingAdapter("android:bind_rv_isNestedScrollingEnabled")
        fun setRecyclerViewIsNestedScrollingEnabled(rv: RecyclerView, isNestedScrollingEnabled: Boolean) {
            rv.isNestedScrollingEnabled = isNestedScrollingEnabled
        }

        /**
         * 设置滑动事件监听
         *
         * @param rv [RecyclerView] 对象
         * @param onScrollStateChanged 滑动状态变化监听
         * @param onScrolled 滑动监听
         */
        @JvmStatic
        @BindingAdapter("android:bind_rv_onScrollStateChanged", "android:bind_rv_onScrolled", requireAll = false)
        fun setRecyclerViewOnScrollListener(rv: RecyclerView, onScrollStateChanged: ((RecyclerView, Int) -> Unit)?, onScrolled: ((RecyclerView, Int, Int) -> Unit)?) {
            rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    onScrollStateChanged?.invoke(recyclerView, newState)
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    onScrolled?.invoke(recyclerView, dx, dy)
                }
            })
        }

        /**
         * 设置布局管理器
         *
         * @param rv [RecyclerView] 对象
         * @param manager 布局管理器
         */
        @JvmStatic
        @BindingAdapter("android:bind_rv_layoutManager")
        fun setRecyclerViewLayoutManager(rv: RecyclerView, manager: RecyclerView.LayoutManager?) {
            rv.layoutManager = manager
        }

        /**
         * 设置布局管理器
         *
         * @param rv [RecyclerView] 对象
         * @param manager 布局管理器
         * - layout:linear,orientation:horizontal,reverse:false,spanCount:1
         */
        @JvmStatic
        @BindingAdapter("android:bind_rv_layoutManager")
        fun setRecyclerViewLayoutManager(rv: RecyclerView, manager: String) {
            // 根据 "," 分割，获取属性集合
            val attrs = manager.split(",")
            // 根据 ":" 分割，获取属性
            val attrsMap = hashMapOf<String, String>()
            attrs.forEach {
                val attr = it.split(":")
                if (attr.isNotEmpty() && attr.size >= 2) {
                    attrsMap[attr[0]] = attr[1]
                }
            }
            rv.layoutManager = if (attrsMap.containsKey("layout")) {
                // 配置了 layout 属性
                when (attrsMap["layout"]) {
                    "linear" -> {
                        // 线性布局
                        LinearLayoutManager(null).apply {
                            orientation = if (attrsMap.containsKey("orientation") && attrsMap["orientation"] == "horizontal") {
                                // 有 orientation 属性且为 horizontal，设置布局方式为 horizontal
                                LinearLayoutManager.HORIZONTAL
                            } else {
                                // 没有 orientation 属性或 不为 horizontal，设置布局方式为 vertical
                                LinearLayoutManager.VERTICAL
                            }
                            if (attrsMap.containsKey("reverse") && attrsMap["reverse"] == "true") {
                                // 有 reverse 属性且值为 "true"，设置反转
                                reverseLayout = true
                                stackFromEnd = true
                            }
                        }
                    }
                    "grid" -> {
                        // 网格布局
                        GridLayoutManager(null, attrsMap["spanCount"]?.toIntOrNull() ?: 2).apply {
                            orientation = if (attrsMap.containsKey("orientation") && attrsMap["orientation"] == "horizontal") {
                                // 有 orientation 属性且为 horizontal，设置布局方式为 horizontal
                                GridLayoutManager.HORIZONTAL
                            } else {
                                // 没有 orientation 属性或 不为 horizontal，设置布局方式为 vertical
                                GridLayoutManager.VERTICAL
                            }
                            if (attrsMap.containsKey("reverse") && attrsMap["reverse"] == "true") {
                                // 有 reverse 属性且值为 "true"，设置反转
                                reverseLayout = true
                                stackFromEnd = true
                            }
                        }
                    }
                    "staggered" -> {
                        // 瀑布流布局
                        if (attrsMap["spanCount"] == null) {
                            throw RuntimeException("The attribute \"spanCount\" for StaggeredGridLayoutManager must not be null!")
                        }
                        // 每行/列数量
                        val spanCount = attrsMap["spanCount"]?.toIntOrNull() ?: 2
                        // 布局方向
                        val orientation = if (attrsMap.containsKey("orientation") && attrsMap["orientation"] == "horizontal") {
                            // 有 orientation 属性且为 horizontal，设置布局方式为 horizontal
                            GridLayoutManager.HORIZONTAL
                        } else {
                            // 没有 orientation 属性或 不为 horizontal，设置布局方式为 vertical
                            GridLayoutManager.VERTICAL
                        }
                        StaggeredGridLayoutManager(spanCount, orientation)
                    }
                    "flow" -> {
                        // 流式布局
                        FlowLayoutManager()
                    }
                    else -> {
                        // 其他，线性布局
                        LinearLayoutManager(null)
                    }
                }
            } else {
                // 没有任何属性字段，默认线性布局
                LinearLayoutManager(null)
            }
        }

        /**
         * 设置适配器
         *
         * @param rv [RecyclerView] 对象
         * @param adapter 适配器
         */
        @JvmStatic
        @BindingAdapter("android:bind_rv_adapter")
        fun setRecyclerViewAdapter(rv: RecyclerView, adapter: RecyclerView.Adapter<*>?) {
            rv.adapter = adapter
        }
    }
}