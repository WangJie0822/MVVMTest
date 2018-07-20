package cn.wj.android.test.application

import android.app.Application
import cn.wj.android.common.utils.AppManager
import cn.wj.android.test.dagger.DaggerMyApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * 当前应用 [Application] 类
 */
class MyApplication : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()

        AppManager.init(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerMyApplicationComponent.builder().create(this)
    }
}