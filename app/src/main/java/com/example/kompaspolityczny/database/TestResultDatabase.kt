package com.example.kompaspolityczny.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TestResult::class], version = 5, exportSchema = false)
abstract class TestResultDatabase : RoomDatabase() {

    abstract val testResultDatabaseDao: TestResultDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: TestResultDatabase? = null

        fun getInstance(context: Context): TestResultDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TestResultDatabase::class.java,
                        "test_results_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}