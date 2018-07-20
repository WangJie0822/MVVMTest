package cn.wj.android.common.mvvm

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 与 Dagger2 配合使用
 */
@Singleton
class DaggerViewModelFactory @Inject constructor(app: Application, private val viewModels: MutableMap<Class<out ViewModel>, ViewModel>)
    : ViewModelProvider.AndroidViewModelFactory(app) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModels[modelClass] as T
}