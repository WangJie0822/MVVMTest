package cn.wj.android.common.ui

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import cn.wj.android.common.BR
import cn.wj.android.common.R
import cn.wj.android.common.mvvm.BaseView
import cn.wj.android.common.mvvm.BaseViewModel
import cn.wj.android.common.utils.AppManager
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

/**
 * Activity 基类
 *
 * @param VM MVVM ViewModel 类，继承 [BaseViewModel]
 * @param V MVVM View 类，继承 [BaseView]
 */
abstract class BaseActivity<VM : BaseViewModel<V>, V : BaseView, DB : ViewDataBinding>
    : AppCompatActivity(),
        HasFragmentInjector,
        HasSupportFragmentInjector,
        BaseView {

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var frameworkFragmentInjector: DispatchingAndroidInjector<android.app.Fragment>

    /** 当前界面 Context 对象*/
    protected lateinit var mContext: AppCompatActivity

    /** ViewModel 工厂类对象 */
    @Inject
    protected lateinit var modelFactory: ViewModelProvider.Factory

    /** 当前界面 ViewModel 对象 */
    protected lateinit var mViewModel: VM

    /** 标记 - ViewModel 是否已经初始化 */
    protected val isViewModelInitialized: Boolean
        get() = ::mViewModel.isInitialized

    /** DataBinding 对象 */
    protected lateinit var mBinding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        // Dagger 注入
        AndroidInjection.inject(this)
        // 初始化 ViewModel
        @Suppress("UNCHECKED_CAST")
        mViewModel = ViewModelProviders.of(this, modelFactory)[(javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>]

        super.onCreate(savedInstanceState)

        // 保存当前 Context 对象
        mContext = this

        // 添加到 AppManager 应用管理
        AppManager.onCreate(this)

        // 初始化数据
        initBefore()

        // 绑定 MVVM
        @Suppress("UNCHECKED_CAST")
        mViewModel.attach(this as? V)
        // 添加生命周期监听
        lifecycle.addObserver(mViewModel)
    }

    override fun setContentView(layoutResID: Int) {
        // 初始化 DataBinding
        mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                layoutResID, null, false
        )

        // 绑定 ViewModel
        mBinding.setVariable(BR.viewModel, mViewModel)

        // 设置布局
        super.setContentView(mBinding.root)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 处理界面返回数据
        mViewModel.onActivityResult(requestCode, resultCode, data)
    }

    override fun onPause() {
        super.onPause()
        // 移除当前获取焦点控件的焦点，防止下个界面软键盘顶起布局
        currentFocus?.clearFocus()
    }

    override fun onDestroy() {

        // 从应用管理移除当前 Activity 对象
        AppManager.onDestroy(this)

        super.onDestroy()

        // 移除生命周期监听
        lifecycle.removeObserver(mViewModel)
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        // 设置动画
        startAnim()
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
        // 设置动画
        startAnim()
    }

    override fun finish() {
        super.finish()
        // 设置动画
        finishAnim()
    }

    override fun supportFragmentInjector() = supportFragmentInjector

    override fun fragmentInjector() = frameworkFragmentInjector

    /**
     * 隐藏底部虚拟菜单栏
     */
    protected fun hideBottomUIMenu() {
        if (Build.VERSION.SDK_INT in Build.VERSION_CODES.HONEYCOMB_MR1..Build.VERSION_CODES.JELLY_BEAN_MR2) {
            // API 12 .. 18
            window.decorView.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // API 19 ..
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }

    /**
     * 开始动画，默认从右侧滑入，可按需求重写
     */
    protected open fun startAnim() {
        overridePendingTransition(R.anim.anim_right_in, R.anim.anim_alpha_out)
    }

    /**
     * 结束动画，默认向右侧滑出，可按需求重写
     */
    protected open fun finishAnim() {
        overridePendingTransition(R.anim.anim_alpha_in, R.anim.anim_right_out)
    }

    /**
     * 初始化，最先调用
     */
    protected open fun initBefore() {}
}