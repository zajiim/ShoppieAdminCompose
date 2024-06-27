package com.example.shoppieadmin

import android.app.Application
import com.cloudinary.android.MediaManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShoppieAdminApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initCloudinary()
    }

    private fun initCloudinary() {
        val config = HashMap<String, Any>()
        config["cloud_name"] = "dxv0jqolp"
        config["api_key"] = "322337555754442"
        config["api_secret"] = "XiKfvz_NMMB-BSGd36Q84bVVM84"
        MediaManager.init(this, config)
    }
}