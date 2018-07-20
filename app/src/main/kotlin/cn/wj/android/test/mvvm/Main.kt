package cn.wj.android.test.mvvm

import android.app.Application
import cn.wj.android.common.mvvm.BaseView
import cn.wj.android.common.mvvm.BaseViewModel
import cn.wj.android.common.utils.ToastUtil
import javax.inject.Inject

class MainViewModel @Inject constructor(application: Application)
    : BaseViewModel<MainView>(application) {

    val str = "哈哈哈哈-main"

    /** 文本点击 */
    val onClick: () -> Unit = {
        ToastUtil.show(str + "\n" + "\n" + getApplication() + "\n")
    }
}

interface MainView : BaseView