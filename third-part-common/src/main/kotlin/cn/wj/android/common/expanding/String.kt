package cn.wj.android.common.expanding

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.regex.Pattern

/**
 * 对 json 字符串进行格式化
 *
 * @return json 格式化完成字符串
 */
fun String.jsonFormat(): String {
    val json = this.trim()
    return try {
        when {
            json.startsWith("{") -> JSONObject(json).toString(2)
            json.startsWith("[") -> JSONArray(json).toString(2)
            else -> "Invalid Json"
        }
    } catch (e: JSONException) {
        Log.e("NET_INTERCEPTOR", "Json Format Error", e)
        "Invalid Json"
    }
}

/**
 * 字符串非空类型转换
 * - 调用对象为 null，则从候选列表中获取
 * - 若调用对象为 null，且获选列表为空或都为 null，则返回空字符串
 *
 * @param str 候选字符串列表
 */
fun String?.orEmpty(vararg str: String?): String {
    if (str.isEmpty()) {
        // 没有参数
        return this ?: ""
    } else {
        // 有参数
        return if (this.isNullOrEmpty()) {
            var ret: String? = null
            for (s in str) {
                if (!s.isNullOrEmpty()) {
                    ret = s ?: ""
                    break
                }
            }
            ret ?: ""
        } else {
            this ?: ""
        }
    }
}

/**
 * 判断字符串是否不为 null 且不为空
 */
fun String?.isNotNullAndEmpty(): Boolean {
    return !this.isNullOrEmpty()
}

/**
 * 检测字符串是否满足 Email 格式
 *
 * @return 字符串是否是 Email
 */
fun String.isEmail(): Boolean {
    return this.regex("^([a-zA-Z0-9_\\-.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$")
}

/**
 * 检测字符串是否满足手机号格式
 * - 规则
 *
 * @return 字符串是否是手机号码
 */
fun String.isPhone(): Boolean {
    return this.regex("^[1]+\\d{10}")
}

fun String.isNotPhone(): Boolean = !this.isPhone()

/**
 * 判断字符串是否与正则匹配
 *
 * @param regex 正则表达式
 *
 * @return 是否匹配
 */
fun String.regex(regex: String): Boolean {
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

/**
 * 检查字符串是否满足密码格式
 *
 * 6-20位英文字符、下划线、数字组合
 */
fun String.meetPwd(): Boolean {
    return this.regex("^\\w{6,20}$")
}


/**
 * 判断字符串中是否包含 Emoji
 *
 * @return 是否包含 Emoji
 */
fun String.containsEmoji(): Boolean {
    return (0 until this.length)
            .map { this[it].toInt() }
            .none {
                (it == 0x0) || (it == 0x9) || (it == 0xA) || (it == 0xD)
                        || (it in 0x20..0xD7FF)
                        || (it in 0xE000..0xFFFD)
                        || (it in 0x10000..0x10FFFF)
            }
}

/**
 * 设置字符串文本不同颜色
 *
 * @param divide 开始标记符号 默认 "+"
 * @param len 不同颜色的长度 默认 2
 * @param color 不同颜色的颜色值 默认红色
 */
fun String.diff(divide: String = "+", len: Int = 2, color: Int = Color.RED): Spannable {
    val index = this.indexOf(divide)
    val span = SpannableString(this)
    span.setSpan(ForegroundColorSpan(color), index, index + len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return span
}

/**
 * 设置字符串文本不同颜色
 *
 * @param start 开始下标
 * @param len 不同颜色的长度 默认 2
 * @param color 不同颜色的颜色值 默认红色
 */
fun String.diff(start: Int, len: Int = 2, color: Int = Color.RED): Spannable {
    val span = SpannableString(this)
    span.setSpan(ForegroundColorSpan(color), start, start + len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return span
}