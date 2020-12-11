package com.ldg.icall

import android.app.Application
import android.content.Context

/**
 * Created by gui on 2020/12/11
 */
class App : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        context = this
    }
}