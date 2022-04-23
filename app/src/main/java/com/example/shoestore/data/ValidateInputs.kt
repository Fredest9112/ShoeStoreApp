package com.example.shoestore.data

import android.content.res.Resources
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.shoestore.R

object ValidateInputs {

    fun validateInput(input: String?, resources: Resources): String {
        return if(input.isNullOrEmpty()){
            resources.getString(R.string.empty)
        } else{
            input
        }
    }

    fun validateSizeInput(size: String?, resources: Resources): String {
        return if (size.isNullOrEmpty()) {
            resources.getString(R.string.empty)
        } else {
            size.toDouble().toString()
        }
    }

    fun validateLoginCredentials(
        name: String,
        pass: String,
        resources: Resources,
        activity: FragmentActivity?
    ): Boolean {
        return if (name.isNotEmpty() && pass.isNotEmpty()) {
            true
        } else {
            Toast.makeText(
                activity,
                resources.getString(R.string.error_no_credentials),
                Toast.LENGTH_SHORT
            ).show()
            false
        }
    }
}