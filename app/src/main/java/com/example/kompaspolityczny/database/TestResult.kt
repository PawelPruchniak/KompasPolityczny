package com.example.kompaspolityczny.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="test_results_database")
data class TestResult(

    @PrimaryKey(autoGenerate = true)
    var resultId: Long = 0L,

    @ColumnInfo(name = "test_date")
    var testDate: Long = 0L,

    @ColumnInfo(name = "gospodarka_left")
    var gospodarkaLeft: Int = 0,

    @ColumnInfo(name = "gospodarka_right")
    var gospodarkaRight: Int = 0,

    @ColumnInfo(name = "politykaW_left")
    var politykaWLeft: Int = 0,

    @ColumnInfo(name = "politykaW_right")
    var politykaWRight: Int = 0,

    @ColumnInfo(name = "politykaZ_left")
    var politykaZLeft: Int = 0,

    @ColumnInfo(name = "politykaZ_right")
    var politykaZRight: Int = 0,

    @ColumnInfo(name = "spoleczenstwo_left")
    var spoleczenstwoLeft: Int = 0,

    @ColumnInfo(name = "spoleczenstwo_right")
    var spoleczenstwoRight: Int = 0

)