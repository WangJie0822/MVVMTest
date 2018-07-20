package cn.wj.android.common.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * SharedPreferences 工具类
 */
@Suppress("unused")
object SharedPrefUtil {

    /** SharedPreferences 文件名 */
    private const val SP_FILE_NAME = "KOTLIN_SHARED_PREF_LIANGYIBANG_DOCTOR"
    /** SharedPreferences 对象 */
    private val sharedPref = AppManager.getApplication().getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE)

    /**
     * 获取默认 SharedPreferences 对象
     */
    fun getSharedPref(): SharedPreferences {
        return sharedPref
    }

    /**
     * 根据名称获取 SharedPreferences 对象
     *
     * @param name 文件名
     */
    fun getSharedPref(name: String): SharedPreferences {
        return AppManager.getApplication().getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    /**
     * 保存字符串
     *
     * @param key   键名
     * @param value 值
     */
    fun put(key: String, value: String) {
        sharedPref.edit().putString(key, value).apply()
    }

    /**
     * 获取字符串
     *
     * @param key      键名
     * @param defValue 默认值
     *
     * @return 字符串
     */
    fun get(key: String, defValue: String) = sharedPref.getString(key, defValue)!!

    /**
     * 保存整形
     *
     * @param key   键名
     * @param value 值
     */
    fun put(key: String, value: Int) {
        sharedPref.edit().putInt(key, value).apply()
    }

    /**
     * 获取整形
     *
     * @param key      键名
     * @param defValue 默认值
     *
     * @return 整形
     */
    fun get(key: String, defValue: Int) = sharedPref.getInt(key, defValue)

    /**
     * 保存长整形
     *
     * @param key   键名
     * @param value 值
     */
    fun put(key: String, value: Long) {
        sharedPref.edit().putLong(key, value).apply()
    }

    /**
     * 获取长整形
     *
     * @param key      键名
     * @param defValue 默认值
     *
     * @return 长整形
     */
    fun get(key: String, defValue: Long) = sharedPref.getLong(key, defValue)

    /**
     * 保存布尔类型
     *
     * @param key   键名
     * @param value 值
     */
    fun put(key: String, value: Boolean) {
        sharedPref.edit().putBoolean(key, value).apply()
    }

    /**
     * 获取布尔值
     *
     * @param key      键名
     * @param defValue 默认值
     *
     * @return 布尔值
     */
    fun get(key: String, defValue: Boolean) = sharedPref.getBoolean(key, defValue)

    /**
     * 根据 key，从 SharedPref 中移除数据
     *
     * @param key 键名
     */
    fun remove(key: String) {
        sharedPref.edit().remove(key).apply()
    }

    /**
     * 获取 SharedPref 中所有数据集合
     *
     * @return 保存的所有数据集合
     */
    fun getAll(): Map<String, *> = sharedPref.all

}