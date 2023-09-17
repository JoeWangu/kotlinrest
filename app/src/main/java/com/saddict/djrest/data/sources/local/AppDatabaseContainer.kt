package com.saddict.djrest.data.sources.local

import com.saddict.djrest.data.sources.AppDaoRepository

interface AppDatabaseContainer {
    val productsDaoRepository: AppDaoRepository
}

//class AppDatabase(private val context: Context) : AppDatabaseContainer {
//    override val productsDaoRepository: AppDaoRepository by lazy {
//        OfflineAppRepository(ProductDatabase.getDatabase(context).productDao())
//    }
//}