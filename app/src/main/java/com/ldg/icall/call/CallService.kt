package com.ldg.icall.call

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager

/**
 * 监听来电状态
 * Created by gui on 2020/12/11
 */
class CallService : Service() {


    companion object {
        @JvmStatic
        fun listenCall(context: Context) {
            context.startService(Intent(context, CallService::class.java))
        }
    }

    private lateinit var callListener: CallStateListener
    private lateinit var tm: TelephonyManager

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        listen()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun listen() {
        callListener = CallStateListener(this.applicationContext)
        tm = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager;
        tm.listen(callListener, PhoneStateListener.LISTEN_CALL_STATE)
    }

}