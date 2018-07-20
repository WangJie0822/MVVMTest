package cn.wj.android.common.ui

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cn.wj.android.common.mvvm.BaseView
import cn.wj.android.common.mvvm.BaseViewModel
import dagger.android.support.DaggerFragment
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

/**
 * Fragment基类
 *
 * @param VM MVVM ViewModel 类，继承 [BaseViewModel]
 * @param V MVVM View 类，继承 [BaseView]
 */
abstract class BaseFragment<VM : BaseViewModel<V>, V : BaseView>
    : DaggerFragment(),
        BaseView {

    /** 当前界面 Context 对象*/
    lateinit var mContext: AppCompatActivity

    /** ViewModel 工厂类对象 */
    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    /** 当前界面 ViewModel 对象 */
    protected lateinit var mViewModel: VM

    /** 标记 - ViewModel 是否已经初始化 */
    protected val isViewModelInitialized: Boolean
        get() = ::mViewModel.isInitialized

    /** 页面标题 */
    open val mPageTitle: String? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        // 初始化 ViewModel
        @Suppress("UNCHECKED_CAST")
        mViewModel = ViewModelProviders.of(this, modelFactory)[(javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 保存当前 Context 对象
        mContext = activity as AppCompatActivity

        // 初始化数据
        initBefore()

        // 绑定 MVVM
        @Suppress("UNCHECKED_CAST")
        mViewModel.attach(this as? V)
        // 添加生命周期监听
        lifecycle.addObserver(mViewModel)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel.onActivityCreated(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 初始界面返回数据
        mViewModel.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()

        // 移除生命周期监听
        lifecycle.removeObserver(mViewModel)
    }

    /**
     * 初始化，最先调用
     */
    protected open fun initBefore() {}
}