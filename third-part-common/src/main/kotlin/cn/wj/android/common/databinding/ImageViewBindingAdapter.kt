package cn.wj.android.common.databinding

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.ImageView
import cn.wj.android.common.expanding.isNotNullAndEmpty
import cn.wj.android.common.expanding.orFalse
import cn.wj.android.common.glide.GlideApp
import java.io.File

/**
 * ViewPager DataBinding 适配器
 */
class ImageViewBindingAdapter {

    companion object {

        /**
         * 根据资源 id 加载图片
         *
         * @param iv    [ImageView] 对象
         * @param resID 图片资源 id
         */
        @JvmStatic
        @BindingAdapter("android:src")
        fun src(iv: ImageView, resID: Int) {
            if (0 != resID) {
                iv.setImageResource(resID)
            }
        }

        /**
         * 加载图片
         *
         * @param iv     [ImageView] 对象
         * @param imgUrl 图片 Url
         * @param placeholder 占位图片
         * @param default 默认图片
         */
        @JvmStatic
        @BindingAdapter("android:bind_iv_img_url", "android:bind_iv_img_placeholder", "android:bind_iv_img_default", "android:bind_iv_img_circle", requireAll = false)
        fun setImageViewUrl(iv: ImageView, imgUrl: String?, placeholder: Drawable?, default: Drawable?, circle: Boolean?) {
            val request = GlideApp.with(iv.context)
                    .load(imgUrl)
            if (null != placeholder) {
                request.placeholder(placeholder)
            }
            if (null != default) {
                request.error(default)
            }
            if (circle.orFalse()) {
                request.circleCrop()
            }
            request.into(iv)
        }

        /**
         * 加载图片
         *
         * @param iv     [ImageView] 对象
         * @param path 图片地址
         * @param placeholder 占位图片
         * @param default 默认图片
         */
        @JvmStatic
        @BindingAdapter("android:bind_iv_img_path", "android:bind_iv_img_placeholder", "android:bind_iv_img_default", "android:bind_iv_img_circle", requireAll = false)
        fun setImageViewPath(iv: ImageView, path: String?, placeholder: Drawable?, default: Drawable?, circle: Boolean?) {
            if (path.isNotNullAndEmpty()) {
                val request = GlideApp.with(iv.context)
                        .load(File(path))
                if (null != placeholder) {
                    request.placeholder(placeholder)
                }
                if (null != default) {
                    request.error(default)
                }
                if (circle.orFalse()) {
                    request.circleCrop()
                }
                request.into(iv)
            } else if (default != null) {
                val request = GlideApp.with(iv.context)
                        .load(default)
                if (null != placeholder) {
                    request.placeholder(placeholder)
                }
                request.error(default)
                if (circle.orFalse()) {
                    request.circleCrop()
                }
                request.into(iv)
            }
        }

        /**
         * 加载图片
         *
         * @param iv     [ImageView] 对象
         * @param img 图片路径
         * @param placeholder 占位图片
         * @param default 默认图片
         */
        @JvmStatic
        @BindingAdapter("android:bind_iv_img", "android:bind_iv_img_placeholder", "android:bind_iv_img_default", "android:bind_iv_img_circle", requireAll = false)
        fun setImageViewImg(iv: ImageView, img: String?, placeholder: Drawable?, default: Drawable?, circle: Boolean?) {
            if (img.isNotNullAndEmpty()) {
                if (img!!.contains("http:") || img.contains("https:")) {
                    // url
                    setImageViewUrl(iv, img, placeholder, default, circle)
                } else {
                    // path
                    setImageViewPath(iv, img, placeholder, default, circle)
                }
            } else if (default != null) {
                val request = GlideApp.with(iv.context)
                        .load(default)
                if (null != placeholder) {
                    request.placeholder(placeholder)
                }
                request.error(default)
                if (circle.orFalse()) {
                    request.circleCrop()
                }
                request.into(iv)
            }
        }

        /**
         * 根据 id 字符串加载图片
         *
         * @param iv [ImageView] 对象
         * @param res 资源字符串
         */
        @JvmStatic
        @BindingAdapter("android:bind_drawable_src")
        fun setStringDrawableSrc(iv: ImageView, res: String) {
            if (res.isNotEmpty()) {
                iv.setImageResource(iv.context.resources.getIdentifier(res, "drawable", iv.context.packageName))
            }
        }

        /**
         * 根据 id 字符串加载图片
         *
         * @param iv [ImageView] 对象
         * @param res 资源字符串
         */
        @JvmStatic
        @BindingAdapter("android:bind_mipmap_src")
        fun setStringMipmapSrc(iv: ImageView, res: String) {
            if (res.isNotEmpty()) {
                iv.setImageResource(iv.context.resources.getIdentifier(res, "mipmap", iv.context.packageName))
            }
        }
    }
}