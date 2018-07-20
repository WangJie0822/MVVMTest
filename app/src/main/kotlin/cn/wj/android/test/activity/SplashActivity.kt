package cn.wj.android.test.activity

import android.os.Bundle
import cn.wj.android.common.ui.BaseActivity
import cn.wj.android.test.R
import cn.wj.android.test.databinding.AppActivitySplashBinding
import cn.wj.android.test.mvvm.SplashView
import cn.wj.android.test.mvvm.SplashViewModel

/**
 * 应用入口 - 闪屏界面
 */
class SplashActivity
    : BaseActivity<SplashViewModel, SplashView, AppActivitySplashBinding>(),
        SplashView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity_splash)
    }
}
