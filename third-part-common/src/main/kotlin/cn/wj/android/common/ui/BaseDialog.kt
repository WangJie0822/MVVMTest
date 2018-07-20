package cn.wj.android.common.ui

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.*
import cn.wj.android.common.expanding.dip2px
import cn.wj.android.common.expanding.round
import cn.wj.android.common.mvvm.BaseView
import cn.wj.android.common.mvvm.BaseViewModel
import dagger.android.support.DaggerAppCompatDialogFragment
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

/**
 * Dialog 弹窗基类
 *
 * @param VM MVVM ViewModel 类，继承 [BaseViewModel]
 * @param V MVVM View 类，继承 [BaseView]
 */
abstract class BaseDialog<VM : BaseViewModel<V>, V : BaseView>
    : DaggerAppCompatDialogFragment(),
        BaseView {

    /** Context 对象 */
    lateinit var mContext: AppCompatActivity

    /** ViewModel 工厂类对象 */
    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    /** 当前界面 ViewModel 对象 */
    protected lateinit var mViewModel: VM

    /** 标记 - ViewModel 是否已经初始化 */
    protected val isViewModelInitialized: Boolean
        get() = ::mViewModel.isInitialized

    /** Dialog 宽度 单位：px  */
    protected var mDialogWidth: Int = 300.dip2px().round()
    /** Dialog 高度 单位：px */
    protected var mDialogHeight: Int = WindowManager.LayoutParams.WRAP_CONTENT
    /** Dialog 重心 [Gravity] */
    protected var mGravity: Int = Gravity.CENTER

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        // 初始化 ViewModel
        @Suppress("UNCHECKED_CAST")
        mViewModel = ViewModelProviders.of(this, modelFactory)[(javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 保存 Context 对象
        mContext = activity as AppCompatActivity

        // 初始化数据
        initBefore()

        // 绑定 MVVM
        @Suppress("UNCHECKED_CAST")
        mViewModel.attach(this as? V)
        // 添加生命周期监听
        lifecycle.addObserver(mViewModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // 取消标题栏
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window.setBackgroundDrawable(ColorDrawable(0x00000000))

        return createView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // 配置 Dialog 宽高、重心
        val layoutParams = dialog.window?.attributes
        layoutParams?.width = mDialogWidth
        layoutParams?.height = mDialogHeight
        layoutParams?.gravity = mGravity
        dialog.window?.attributes = layoutParams
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
     * 创建 Dialog View
     */
    abstract fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?

    /**
     * 初始化，最先调用
     */
    protected open fun initBefore() {}
}