package cn.wj.android.common.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule

/**
 * Glide Module
 */
@GlideModule
class MyAppGlideModule : AppGlideModule() {

    companion object {
        /** Glide 图片内存缓存大小 20MB */
        const val GLIDE_MEMORY_CACHE_SIZE = 20L * 1024L * 1024L
        /** Glide 图片磁盘缓存大小 200MB */
        const val GLIDE_DISK_CACHE_SIZE = 200L * 1024L * 1024L
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        // 设置内存缓存大小
        builder.setMemoryCache(LruResourceCache(GLIDE_MEMORY_CACHE_SIZE))
        // 设置磁盘缓存
        builder.setDiskCache(ExternalPreferredCacheDiskCacheFactory(context, "glide_lyb_doctor", GLIDE_DISK_CACHE_SIZE))
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
//        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory())
    }

    override fun isManifestParsingEnabled() = false
}