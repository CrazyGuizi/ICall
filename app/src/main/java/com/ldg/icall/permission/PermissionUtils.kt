package com.ldg.icall.permission

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * Created by gui on 2020/12/11
 */
object PermissionUtils {


    /**
     * 打开悬浮框权限
     *
     * @param context
     */
    fun gotoCanOverly(context: Context) {
        try {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + context.packageName))
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 悬浮框
     *
     * @param context
     * @return
     */
    fun hasCanOverly(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Settings.canDrawOverlays(context.applicationContext)
        } else {
            true
        }
    }

    fun checkExternal(context: Context): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                    .any { ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED }
        }

        return true
    }

    fun getExternal(context: Context, reqCode: Int) {

    }

    fun checkAnswerCall(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ANSWER_PHONE_CALLS) != PackageManager.PERMISSION_GRANTED
    }

    fun grantAnswerCall(activity: Activity, reqCode: Int) {
        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ANSWER_PHONE_CALLS), reqCode)
    }

}