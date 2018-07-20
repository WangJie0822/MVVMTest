package cn.wj.android.common.utils

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.util.*

/**
 * 应用程序 Activity 管理类：用于 Activity 管理和应用程序退出
 */
@Suppress("unused")
object AppManager {

    /** Application 对象 */
    private lateinit var mApplication: Application

    /** 保存 Activity 对象的堆栈 */
    private val activityStack: Stack<AppCompatActivity> = Stack()

    /** 忽略列表 */
    private val ignoreActivitys = arrayListOf<Class<out AppCompatActivity>>()

    /**
     * 初始化，保存 Application 对象
     */
    fun init(application: Application) {
        mApplication = application
    }

    /**
     * 获取可用 Application 对象
     */
    fun getApplication(): Application {
        return mApplication
    }

    /**
     * 将 Activity 类对象添加到忽略列表
     */
    fun addToIgnore(vararg clazzs: Class<out AppCompatActivity>) {
        ignoreActivitys.addAll(clazzs)
    }

    /**
     * 添加 Activity 到堆栈
     *
     * @param activity Activity 对象
     */
    fun onCreate(activity: AppCompatActivity) {
        if (ignoreActivitys.contains(activity.javaClass)) {
            // 不添加在忽略列表中的 Activity
            return
        }
        activityStack.add(activity)
        Log.d("Common_AppManager", "add---->>$activity size---->>${activityStack.size}")
    }

    /**
     * 将 Activity 从堆栈移除
     *
     * @param activity Activity 对象
     */
    fun onDestroy(activity: AppCompatActivity) {
        if (activityStack.contains(activity)) {
            activityStack.remove(activity)
            Log.d("Common_AppManager", " remove---->>$activity size---->>${activityStack.size}")
        }
    }

    fun contains(clazz: Class<out AppCompatActivity>): Boolean {
        return activityStack.count { it.javaClass == clazz } > 0
    }

    /**
     * 将 Activity 加入堆栈
     */
    fun add(activity: AppCompatActivity) {
        activityStack.add(activity)
    }

    /**
     * 将 Activity 从堆栈移除
     *
     * @param activity Activity 对象
     */
    fun remove(activity: AppCompatActivity) {
        if (activityStack.contains(activity)) {
            activityStack.remove(activity)
        }
    }

    /**
     * 结束指定 Activity 之外的其他 Activity
     *
     * @param activity 指定不关闭的 Activity
     */
    fun finishAllWithout(activity: AppCompatActivity) {
        remove(activity)
        finishAllActivity()
        add(activity)
    }

    /**
     * 结束指定 Activity
     *
     * @param clazz Activity 类对象
     */
    fun finishActivity(clazz: Class<out AppCompatActivity>) {
        val del: AppCompatActivity? = activityStack.lastOrNull { it.javaClass == clazz }
        del?.finish()
    }

    /**
     * 结束指定 Activity
     *
     * @param clazzs Activity 类对象
     */
    fun finishActivities(vararg clazzs: Class<out AppCompatActivity>) {
        for (clazz in clazzs) {
            finishActivity(clazz)
        }
    }

    /**
     * 获取栈顶的 Activity
     *
     * @return 栈顶的 Activity 对象
     */
    fun peekActivity(): AppCompatActivity {
        return activityStack.peek()
    }

    /**
     * 根据类，获取 Activity 对象
     *
     * @param clazz  Activity 类
     * @param A      Activity 类型
     *
     * @return       Activity对象
     */
    fun <A : AppCompatActivity> getActivity(clazz: Class<A>): A? {
        var target: A? = null
        activityStack
                .filter { it.javaClass == clazz }
                .forEach {
                    @Suppress("UNCHECKED_CAST")
                    target = it as A
                }
        return target
    }

    /**
     * 结束所有 Activity
     */
    private fun finishAllActivity() {
        for (activity in activityStack) {
            activity.finish()
        }
        activityStack.clear()
        Log.d("Common_AppManager", "Finish All Activity!")
    }

    /**
     * 退出应用程序
     */
    fun appExit() {
        try {
            val activityMgr = peekActivity().getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            activityMgr.killBackgroundProcesses(peekActivity().packageName)
            finishAllActivity()
            Log.d("Common_AppManager", " Application Exit!")
            System.exit(0)
        } catch (e: Exception) {
            Log.e("Common_AppManager", "APP_EXIT", e)
        }
    }
}
