package com.nsrdev.crossfit

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nsrdev.crossfit.adapter.RecyclerViewAdapter
import com.nsrdev.crossfit.model.Exercise
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var exerciseViewModel: ExerciseViewModel
    private lateinit var rvadapter: RecyclerViewAdapter
    private val SUCCESS = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        exerciseViewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java)
        rvadapter = RecyclerViewAdapter(exerciseViewModel)
        exerciseViewModel.exercises.observe(this, Observer {
            exercises -> exercises.let { rvadapter.setData(exercises) }
        })

        recyclerView.adapter = rvadapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        addExerciseButton.setOnClickListener {
            val intent = Intent(this, AddExercise::class.java)
            startActivityForResult(intent, SUCCESS)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == SUCCESS && resultCode == Activity.RESULT_OK && data != null) {
            var reps = data.getIntExtra("repetitions", 0)
            val rm = data.getBooleanExtra("rm", false)

            if (rm) reps = 1

            val exercise = Exercise(0,
                data.getStringExtra("name")!!,
                data.getDoubleExtra("weight", 0.0),
                reps,
                rm
            )

            exerciseViewModel.addExercise(exercise)
        }
    }
}