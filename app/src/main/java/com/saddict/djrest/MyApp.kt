package com.saddict.djrest

import android.app.Application
import com.saddict.djrest.data.manager.AppRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp: Application() {
//    lateinit var container: AppDatabaseContainer
//    override fun onCreate() {
//        super.onCreate()
//        container = AppRepository()
//    }
}