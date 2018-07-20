package cn.wj.android.common.mvvm

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 * 提供 ViewModelProvider.Factory 对象
 */
@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
}
