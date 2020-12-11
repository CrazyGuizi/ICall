package com.ldg.icall.call

import android.content.Context
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log
import com.ldg.icall.video.VideoPlayHelper
import com.ldg.icall.video.VideoPlayer

/**
 * Created by gui on 2020/12/11
 */
class CallStateListener(private val context: Context) : PhoneStateListener() {

    override fun onCallStateChanged(state: Int, phoneNumber: String?) {
        super.onCallStateChanged(state, phoneNumber)
        when (state) {
            TelephonyManager.CALL_STATE_IDLE -> { // 待机，即无电话时，挂断时触发
                Log.d("ldg", "onCallStateChanged: IDLE")
                VideoPlayHelper.destroyView()
            }

            TelephonyManager.CALL_STATE_RINGING -> { // 响铃，来电时触发
                Log.d("ldg", "onCallStateChanged: RINGING")
                VideoPlayHelper.showView(context)
            }

            TelephonyManager.CALL_STATE_OFFHOOK -> { // 摘机，接听或拨出电话时触发
                Log.d("ldg", "onCallStateChanged: OFFHOOK")
            }
        }
    }
}