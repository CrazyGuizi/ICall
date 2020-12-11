package com.ldg.icall.video

import android.content.Context
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

/**
 * Created by gui on 2020/12/11
 */

class VideoView : SurfaceView, SurfaceHolder.Callback {
    private lateinit var videoPlayer: VideoPlayer

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        holder.addCallback(this)
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        videoPlayer = VideoPlayer()
        videoPlayer.play(context, holder.surface)
    }

    fun setWidthAndHeight(w: Int, h: Int) {

    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        videoPlayer.release()
    }

}