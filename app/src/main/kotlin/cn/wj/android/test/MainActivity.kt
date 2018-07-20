package cn.wj.android.test

import android.os.Bundle
import cn.wj.android.common.ui.BaseActivity
import cn.wj.android.test.databinding.AppActivityMainBinding
import cn.wj.android.test.mvvm.MainView
import cn.wj.android.test.mvvm.MainViewModel

class MainActivity
    : BaseActivity<MainViewModel, MainView, AppActivityMainBinding>(),
        MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_activity_main)
    }
}
