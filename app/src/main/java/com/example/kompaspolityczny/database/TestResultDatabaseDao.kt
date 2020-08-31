package com.example.kompaspolityczny.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface  TestResultDatabaseDao {

    @Insert
    fun insert(result: TestResult)

    @Update
    fun update(result: TestResult)

    @Delete
    fun delete(result: TestResult)

    @Query("SELECT * FROM test_results_database")
    fun getAllResults(): LiveData<List<TestResult>>
}