package cn.wj.android.test.dagger

import cn.wj.android.test.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Activity Dagger2 Module
 */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}