package cn.wj.android.common.expanding

/**
 * 四舍五入
 *
 * @return 四舍五入后的值
 */
fun <N : Number> N.round() = when (this) {
    is Float -> (this + 0.5F).toInt()
    is Double -> (this + 0.5).toInt()
    else -> this.toInt()
}