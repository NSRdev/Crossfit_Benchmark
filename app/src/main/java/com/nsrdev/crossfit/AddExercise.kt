package com.nsrdev.crossfit

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.nsrdev.crossfit.model.Exercise
import kotlinx.android.synthetic.main.activity_add_exercise.*

class AddExercise : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exercise)

        rmSwitch.setOnCheckedChangeListener { _, b ->
            if (b) {
                repetitionsText.setText("1")
                repetitionsText.isEnabled = false
            } else {
                repetitionsText.isEnabled = true
            }
        }

        saveButton.setOnClickListener {
                val name = spinner.selectedItem.toString()
                val weight = weightText.text.toString().toDouble()
                val repetitions = repetitionsText.text.toString().toInt()
                val rm = rmSwitch.isChecked

            if (name.isNotEmpty() && weight != null) {
                intent.putExtra("name", name)
                intent.putExtra("weight", weight)
                intent.putExtra("repetitions", repetitions)
                intent.putExtra("rm", rm)

                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}