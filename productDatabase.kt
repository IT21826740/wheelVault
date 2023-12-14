package com.example.appca.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Products::class], version = 1, exportSchema = false)
abstract class productDatabase: RoomDatabase() {
    abstract fun getproductDAO():productDAO

    companion object{
        @Volatile
        private var INSTENCE:productDatabase?=null

        fun getInstence(context: Context):productDatabase{
            return INSTENCE ?: synchronized(this){
                INSTENCE?: Room.databaseBuilder(
                    context.applicationContext,
                    productDatabase::class.java,
                    "new_DB"

                ).build().also {
                    INSTENCE=it
                }
            }
        }

    }
}
