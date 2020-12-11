package com.ldg.icall.ring

import android.content.ContentValues
import android.content.Context
import android.media.RingtoneManager
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.text.TextUtils
import android.widget.Toast
import java.io.*

/**
 * 设置铃声
 * Created by gui on 2020/12/11
 */

object RingSetHelper {
    /**
     * 设置铃声
     *
     * @param ringType int： 铃声类型
     * @param file     File： 要设为铃声的文件
     * @param title    标题
     */
    fun setRingtone(context: Context, ringType: Int, file: File, title: String?): Boolean {
        var isRingtone = false
        var isNotification = false
        var isAlarm = false
        var isMusic = false
        var showText = ""
        when (ringType) {
            RingtoneManager.TYPE_ALARM -> {
                isAlarm = true
                showText = "闹钟"
            }
            RingtoneManager.TYPE_NOTIFICATION -> {
                isNotification = true
                showText = "通知"
            }
            RingtoneManager.TYPE_RINGTONE -> {
                isRingtone = true
                showText = "来电"
            }
            RingtoneManager.TYPE_ALL -> {
                isMusic = true
                showText = "全部"
            }
            else -> {
            }
        }
        val values = ContentValues()
        values.put(MediaStore.MediaColumns.DATA, file.absolutePath)
        values.put(MediaStore.MediaColumns.TITLE, if (TextUtils.isEmpty(title)) file.name else title)
        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/*")
        values.put(MediaStore.Audio.Media.IS_RINGTONE, isRingtone)
        values.put(MediaStore.Audio.Media.IS_NOTIFICATION, isNotification)
        values.put(MediaStore.Audio.Media.IS_ALARM, isAlarm)
        values.put(MediaStore.Audio.Media.IS_MUSIC, isMusic)
        val uri = MediaStore.Audio.Media.getContentUriForPath(file.absolutePath) //获取系统音频文件的Uri
        try {
            //删除之前的铃声
            context.contentResolver.delete(uri!!, MediaStore.MediaColumns.DATA + "=\"" + file.absolutePath + "\"", null)
            val newUri = context.contentResolver.insert(uri, values) //将文件插入系统媒体库，并获取新的Uri
            if (newUri != null) {
                RingtoneManager.setActualDefaultRingtoneUri(context, ringType, newUri) //设置铃声
            }

            Toast.makeText(context, "设置" + showText + "铃声成功", Toast.LENGTH_SHORT).show()
            Settings.System.putString(context.contentResolver, "ringtone2", newUri.toString())
            Settings.System.putString(context.contentResolver, "ringtone_sim2", newUri.toString())
            Settings.System.putString(context.contentResolver, "ringtone_2", newUri.toString())
            return true
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun setRing(context: Context) {
        // 获取铃声文件
        val ringFile: File = getRingFile(context)
        setRingtone(context, RingtoneManager.TYPE_RINGTONE, ringFile, "ring_test")
    }

    private fun getRingFile(context: Context): File {
        val file = File(Environment.getExternalStorageDirectory().absolutePath + "/aaa/ring_test.aac")
        val ringPath = "ring_test.aac"
        file.deleteOnExit()
        if (!file.exists()) {
            file.parentFile.mkdirs()
        }
        var os: OutputStream? = null
        try {
            os = FileOutputStream(file)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        try {
            val inputStream: InputStream = context.assets.open(ringPath)
            val buffer = ByteArray(1024)
            var len = 0
            while (inputStream.read(buffer).also { len = it } != -1) {
                os!!.write(buffer, 0, len)
            }
            os!!.flush()
            os.close()
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return file
    }
}