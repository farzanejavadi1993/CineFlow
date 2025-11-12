package com.fermer.dataa.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fermer.dataa.local.dao.MovieDao
import com.fermer.dataa.local.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
