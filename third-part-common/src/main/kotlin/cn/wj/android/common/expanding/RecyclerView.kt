package cn.wj.android.common.expanding

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

/**
 * 判断是否在最顶部
 */
fun RecyclerView.isTop(): Boolean {
    val layoutManager = this.layoutManager
    return when (layoutManager) {
        is LinearLayoutManager -> 0 == layoutManager.findFirstCompletelyVisibleItemPosition()
        is GridLayoutManager -> 0 == layoutManager.findFirstCompletelyVisibleItemPosition()
        is StaggeredGridLayoutManager -> 0 in layoutManager.findFirstCompletelyVisibleItemPositions(null)
        else -> false
    }
}

/**
 * 滑动到最顶部
 */
fun RecyclerView.smoothScrollToTop() {
    this.smoothScrollToPosition(0)
}

/**
 * 滑动到底部
 */
fun RecyclerView.smoothScrollToBottom() {
    if (this.adapter != null) {
        this.smoothScrollToPosition(this.adapter!!.itemCount - 1)
    }
}