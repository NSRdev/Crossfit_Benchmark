package com.nsrdev.crossfit.database

import androidx.lifecycle.LiveData
import com.nsrdev.crossfit.model.Exercise

class Repository(private val exerciseDAO: ExerciseDAO) {

    val exercises: LiveData<List<Exercise>> = exerciseDAO.getAllExercises()

    fun addExercise(exercise: Exercise) {
        exerciseDAO.addExercise(exercise)
    }

    fun removeExercise(exercise: Exercise) {
        exerciseDAO.removeExercise(exercise)
    }

}