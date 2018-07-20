package cn.wj.android.test.dagger

import android.arch.lifecycle.ViewModel
import cn.wj.android.common.mvvm.ViewModelKey
import cn.wj.android.test.mvvm.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun provideMainViewModel(viewModel: SplashViewModel): ViewModel
}