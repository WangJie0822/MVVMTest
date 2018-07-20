package cn.wj.android.test.dagger

import cn.wj.android.common.mvvm.ViewModelFactoryModule
import cn.wj.android.test.application.MyApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/**
 * Application Dagger2 组件
 */
@Singleton
@Component(modules = [
    ActivityModule::class,
    ViewModelModule::class,
    ViewModelFactoryModule::class,
    ApplicationModule::class,
    AndroidSupportInjectionModule::class
])
interface MyApplicationComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyApplication>()
}