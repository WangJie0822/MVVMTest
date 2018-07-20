package cn.wj.android.common.databinding

import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.databinding.InverseBindingListener
import cn.wj.android.common.widget.SideBarView
import cn.wj.android.common.widget.SwitchButton

/**
 * ViewPager DataBinding 适配器
 */
class CustomViewBindingAdapter {

    companion object {

        /**
         * 设置点击事件
         *
         * @param sb [SwitchButton] 对象
         * @param check 选择回调
         */
        @JvmStatic
        @BindingAdapter("android:bind_sb_onCheck")
        fun setSwitchButtonOnCheckChange(sb: SwitchButton, check: ((Boolean) -> Unit)) {
            sb.setOnCheckedChangeListener { _, isChecked -> check(isChecked) }
        }

        /**
         * 设置是否选中
         *
         * @param sb [SwitchButton] 对象
         * @param check 是否选中
         */
        @JvmStatic
        @BindingAdapter("android:bind_sb_check")
        fun setSwitchButtonCheck(sb: SwitchButton, check: Boolean) {
            sb.isChecked = check
        }

        /**
         * 获取是否选中
         *
         * @param sb [SwitchButton] 对象
         *
         * @return 是否选中
         */
        @JvmStatic
        @InverseBindingAdapter(attribute = "android:bind_sb_check")
        fun getSwitchButtonCheck(sb: SwitchButton): Boolean {
            return sb.isChecked
        }

        /**
         * 设置是否选中
         *
         * @param sb [SwitchButton] 对象
         * @param check 是否选中
         */
        @JvmStatic
        @BindingAdapter("android:bind_sb_checkAttrChanged")
        fun setSwitchButtonCheckChange(sb: SwitchButton, check: InverseBindingListener) {
            sb.setOnCheckedChangeListener { _, _ -> check.onChange() }
        }

        /**
         * 设置侧边栏选中状态监听
         *
         * @param sbv [SideBarView] 对象
         * @param selected 选中回调
         * @param changed 变更回调
         * @param released 释放回调
         */
        @JvmStatic
        @BindingAdapter("android:bind_sbv_selected", "android:bind_sbv_changed", "android:bind_sbv_released", requireAll = false)
        fun setSideBarSelectListener(sbv: SideBarView, selected: ((SideBarView, String) -> Unit)?,
                                     changed: ((SideBarView, String) -> Unit)?, released: ((SideBarView, String) -> Unit)?) {
            sbv.setOnLetterSelectListener(object : SideBarView.LetterSelectListener {
                override fun onLetterSelected(letter: String) {
                    selected?.invoke(sbv, letter)
                }

                override fun onLetterChanged(letter: String) {
                    changed?.invoke(sbv, letter)
                }

                override fun onLetterReleased(letter: String) {
                    released?.invoke(sbv, letter)
                }
            })
        }
    }
}