package com.saddict.djrest.data.sources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.saddict.djrest.data.manager.ProductDao
import com.saddict.djrest.model.local.ProductEntity
import com.saddict.djrest.model.local.ProductFavourites
import com.saddict.djrest.utils.Converters

@Database(
    entities = [
        ProductEntity::class,
        ProductFavourites::class
    ], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
//    companion object{
//        @Volatile
//        private var Instance: ProductDatabase? = null

//        fun getDatabase(context: Context): ProductDatabase {
//            return Instance ?: synchronized(this){
//                Room.databaseBuilder(context, ProductDatabase::class.java, "product_database")
//                    .fallbackToDestructiveMigration()
//                    .build()
//                    .also { Instance = it }
//            }
//        }
//    }
}