package cn.wj.android.common.expanding

import com.google.gson.*

/**
 * 从数组、集合 Json 字符串获取数据
 */
fun <T> Gson.fromJsonArray(json: String, clazz: Class<T>): ArrayList<T> {
    val ls = arrayListOf<T>()
    val array = JsonParser().parse(json).asJsonArray
    array.forEach { ls.add(this.fromJson(it, clazz)) }
    return ls
}

/**
 * 将数据对象转换成 Json 字符串
 *
 * @param error 转换异常返回
 */
fun Any.toJson(error: String = ""): String {
    return try {
        Gson().toJson(this)
    } catch (e: JsonIOException) {
        error
    }
}

/**
 * 将数据对象转换成 Json 字符串
 * - 禁用 Html 转义
 *
 * @param error 转换异常返回
 */
fun Any.toJsonWithoutHtmlEscaping(error: String = ""): String {
    return try {
        GsonBuilder().disableHtmlEscaping().create().toJson(this)
    } catch (e: JsonIOException) {
        error
    }
}

/**
 * 将字符串转换成 数据对象
 * - 转换失败返回 null
 */
fun <T> String?.toEntity(clazz: Class<T>): T? {
    return try {
        Gson().fromJson<T>(this, clazz)
    } catch (e: JsonSyntaxException) {
        null
    }
}