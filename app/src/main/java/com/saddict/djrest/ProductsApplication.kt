package com.saddict.djrest

import android.app.Application
import com.saddict.djrest.data.manager.AppRepository
import com.saddict.djrest.data.sources.local.AppDatabaseContainer

class ProductsApplication: Application() {
    lateinit var container: AppDatabaseContainer
    override fun onCreate() {
        super.onCreate()
        container = AppRepository(this)
    }
}