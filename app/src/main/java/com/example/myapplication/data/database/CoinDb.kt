package com.example.myapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DbCryptoCoin::class], version = 1, exportSchema = false)
abstract class CoinDb : RoomDatabase() {

    abstract fun coinDao(): CoinDao

    companion object {
        @Volatile
        private var Instance: CoinDb? = null

        fun getDatabase(context: Context): CoinDb {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, CoinDb::class.java, "coin_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}