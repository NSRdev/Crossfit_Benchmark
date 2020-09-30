package com.nsrdev.crossfit.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    var weight: Double,
    var repetitions: Int,
    var rm: Boolean
)
