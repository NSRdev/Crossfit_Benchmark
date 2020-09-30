package com.nsrdev.crossfit.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nsrdev.crossfit.model.Exercise

@Dao
interface ExerciseDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addExercise(exercise: Exercise)

    @Query("SELECT * FROM exercises ORDER BY name")
    fun getAllExercises(): LiveData<List<Exercise>>

    @Delete
    fun removeExercise(exercise: Exercise)
}
