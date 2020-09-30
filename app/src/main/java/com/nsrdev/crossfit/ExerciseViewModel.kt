package com.nsrdev.crossfit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nsrdev.crossfit.database.CrossfitDB
import com.nsrdev.crossfit.database.Repository
import com.nsrdev.crossfit.model.Exercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ExerciseViewModel(application: Application): AndroidViewModel(application) {

    val exercises: LiveData<List<Exercise>>
    private val repository: Repository

    init {
        val exerciseDAO = CrossfitDB.getCrossfitDB(application).exerciseDAO()
        repository = Repository(exerciseDAO)
        exercises = repository.exercises
    }

    fun addExercise(exercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addExercise(exercise)
        }
    }

    fun removeExercise(exercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeExercise(exercise)
        }
    }
}