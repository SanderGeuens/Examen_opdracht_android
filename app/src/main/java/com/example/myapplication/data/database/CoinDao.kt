package com.example.myapplication.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(coin: DbCryptoCoin)

        @Update
        suspend fun update(item: DbCryptoCoin)

        @Delete
        suspend fun delete(item: DbCryptoCoin)

        @Query("SELECT * from coins ORDER BY rank ASC")
        fun getAllItems(): Flow<List<DbCryptoCoin>>

}