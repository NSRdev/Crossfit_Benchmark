package com.nsrdev.crossfit.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.nsrdev.crossfit.ExerciseViewModel
import com.nsrdev.crossfit.R
import com.nsrdev.crossfit.model.Exercise
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewAdapter(private val exerciseViewModel: ExerciseViewModel): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var exercises = emptyList<Exercise>()
    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.exercise_textView.text = exercises[position].name
        holder.itemView.weight_textView.text = exercises[position].weight.toString() + " kg"

        if(exercises[position].rm) {
            holder.itemView.rmText.text = "RM"
        } else {
            holder.itemView.rmText.text = exercises[position].repetitions.toString() + " reps"
        }

        holder.itemView.deleteButton.setOnClickListener {
            AlertDialog.Builder(context)
                .setMessage("¿Eliminar?")
                .setPositiveButton("Sí") { _, _ ->
                    exerciseViewModel.removeExercise(exercises[position])
                }
                .setNegativeButton("No") { _, _ -> }
                .create()
                .show()
        }
    }

    fun setData(data: List<Exercise>) {
        exercises = data
        notifyDataSetChanged()
    }
}