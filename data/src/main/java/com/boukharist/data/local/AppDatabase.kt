package com.boukharist.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.boukharist.data.local.datasources.BmrLocalDataSource
import com.boukharist.data.local.datasources.UserLocalDataSource
import com.boukharist.data.local.models.BmrDto
import com.boukharist.data.local.models.UserDto

@Database(entities = [BmrDto::class, UserDto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun createBmrLocalDataSource(): BmrLocalDataSource
    abstract fun createUserLocalDataSource(): UserLocalDataSource

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: build(context).also { instance = it }
            }
        }

        private fun build(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "db")
                .build()
        }
    }
}
