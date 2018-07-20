package cn.wj.android.test.mvvm

import android.app.Application
import cn.wj.android.common.mvvm.BaseView
import cn.wj.android.common.mvvm.BaseViewModel
import javax.inject.Inject

/**
 * 应用入口 - 闪屏界面 ViewModel
 */
class SplashViewModel @Inject constructor(application: Application)
    : BaseViewModel<SplashView>(application)

/**
 * 应用入口 - 闪屏界面 View
 */
interface SplashView : BaseView