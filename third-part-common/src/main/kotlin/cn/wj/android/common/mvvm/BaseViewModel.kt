package cn.wj.android.common.mvvm

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * MVVM ViewModule 基类
 * - 继承 [AndroidViewModel] 持有应用 [Application] 对象
 * - 实现 [LifecycleObserver] 监听生命周期
 */
open class BaseViewModel<V : BaseView> @Inject constructor(application: Application)
    : AndroidViewModel(application),
        LifecycleObserver {

    /** View 对象  */
    protected var mView: V? = null

    /** RxJava2 生命周期管理  */
    private var disposables: CompositeDisposable? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() {
        Log.d("Common_MVVM", "View onCreate ---->> $mView")
        disposables = CompositeDisposable()
    }

    /**
     * 仅 Fragment 使用
     */
    open fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("Common_MVVM", "View onActivityCreated ---->> $mView")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {
        Log.d("Common_MVVM", "View onStart ---->> $mView")
    }

    /**
     * 从 Activity 返回且又返回数据
     */
    open fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("Common_MVVM", "View onActivityResult ---->> $mView")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {
        Log.d("Common_MVVM", "View onResume ---->> $mView")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {
        Log.d("Common_MVVM", "View onPause ---->> $mView")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {
        Log.d("Common_MVVM", "View onStop ---->> $mView")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {
        Log.d("Common_MVVM", "View onDestroy ---->> $mView")
        // 接触绑定，消费事件，移除依赖
        detach()
    }

    /**
     * 界面绑定，关联 View
     *
     * @param view View
     */
    fun attach(view: V?) {
        mView = view
    }

    /**
     * 解除绑定，消费事件，去除 View 引用
     */
    private fun detach() {
        disposables?.clear()
        disposables = null
        mView = null
    }

    /**
     * 将网络请求添加到 RxJava2 生命周期
     */
    protected fun addDisposable(dis: Disposable) {
        disposables?.add(dis)
    }
}