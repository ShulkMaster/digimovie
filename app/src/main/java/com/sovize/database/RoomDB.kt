package com.sovize.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sovize.database.daos.MovieDao
import com.sovize.database.entities.Movie

@Database(entities = [Movie::class], version = 2, exportSchema = false)
abstract class RoomDB : RoomDatabase(){

    abstract fun movieDao(): MovieDao

    companion object{
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getDatabase(appContext: Context): RoomDB {
            if (INSTANCE == null) {
                synchronized(RoomDB::class) {
                    INSTANCE = Room
                        .databaseBuilder(appContext.applicationContext
                            , RoomDB::class.java
                            ,"movies_db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}