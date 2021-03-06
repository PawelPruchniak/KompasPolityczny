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

    @Query("SELECT * FROM test_results_database ORDER BY resultId ASC")
    fun getAllResults(): LiveData<List<TestResult>>

    @Query("SELECT * FROM test_results_database ORDER BY resultId DESC LIMIT 1")
    fun getLastResult(): TestResult?

    @Query("DELETE FROM test_results_database")
    fun clear()

    @Query("SELECT * from test_results_database WHERE resultId = :key")
    fun getNightWithId(key: Long): LiveData<TestResult>

    @Query("SELECT * from test_results_database WHERE resultId = :key")
    fun get(key: Long): TestResult

    @Query("DELETE FROM test_results_database WHERE resultId =:key")
    fun deleteById(key: Long)

}