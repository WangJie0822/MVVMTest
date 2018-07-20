package cn.wj.android.common.simple

import android.inputmethodservice.KeyboardView

/**
 * 软键盘输入监听简易实现
 */
abstract class SimpleKeyboardActionListener : KeyboardView.OnKeyboardActionListener {
    override fun swipeRight() {
    }

    override fun onPress(primaryCode: Int) {
    }

    override fun onRelease(primaryCode: Int) {
    }

    override fun swipeLeft() {
    }

    override fun swipeUp() {
    }

    override fun swipeDown() {
    }

    override fun onKey(primaryCode: Int, keyCodes: IntArray?) {
    }

    override fun onText(text: CharSequence?) {
    }

}