package com.ldg.icall.video

import android.content.Context
import android.media.MediaPlayer
import android.view.Surface
import com.ldg.icall.R

/**
 * Created by gui on 2020/12/11
 */
class VideoPlayer {

    private lateinit var mPlayer: MediaPlayer

    fun play(context: Context, surface: Surface) {
        if (!::mPlayer.isInitialized) {
            mPlayer = MediaPlayer.create(context.applicationContext, R.raw.liuyifei)
        }

        mPlayer.run {
            isLooping = true
            setVolume(0F, 0F)
            setSurface(surface)
        }

        mPlayer.setOnPreparedListener { mPlayer.start() }
    }

    fun release() {
        if (::mPlayer.isInitialized) {
            mPlayer.run {
                stop()
                release()
            }
        }
    }

}