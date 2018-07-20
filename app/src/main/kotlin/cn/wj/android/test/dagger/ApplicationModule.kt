package cn.wj.android.test.dagger

import android.app.Application
import android.content.Context
import cn.wj.android.test.application.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Application Module
 */
@Module
class ApplicationModule {

    /**
     * 提供当前应用 [Application] 对象
     * - 单例对象
     */
    @Singleton
    @Provides
    fun provideApplication(app: MyApplication): Application = app

    /**
     * 提供 [Context] 对象
     * - 单例对象
     */
    @Singleton
    @Provides
    fun provideContext(app: MyApplication): Context = app
}