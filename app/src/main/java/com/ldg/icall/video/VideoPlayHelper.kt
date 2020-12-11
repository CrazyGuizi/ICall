package com.ldg.icall.video

import android.content.Context
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.WindowManager

/**
 * Created by gui on 2020/12/11
 */
object VideoPlayHelper {

    private lateinit var videoGroup: VideoGroup
    private lateinit var wm: WindowManager

    fun showView(context: Context) {
        wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val layoutParams = WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        layoutParams.run {
            packageName = context.packageName
            flags = (WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    or WindowManager.LayoutParams.FLAG_SCALED
                    or WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR
                    or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
            type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            format = PixelFormat.RGBA_8888
            gravity = Gravity.START or Gravity.TOP
            x = 0
            y = 0
            width = context.resources.displayMetrics.widthPixels
            height = context.resources.displayMetrics.heightPixels
        }
        videoGroup = VideoGroup(context.applicationContext)
        wm.addView(videoGroup, layoutParams)
    }

    fun destroyView() {
        if (::wm.isInitialized) {
            wm.removeView(videoGroup)
        }
    }
}