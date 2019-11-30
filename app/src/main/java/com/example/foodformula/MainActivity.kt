package com.example.foodformula

import android.app.ProgressDialog
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.foodformula.ApiConnection.Models.Recipe
import com.example.foodformula.ApiConnection.Status
import com.example.foodformula.ViewModels.ActivityViewModel
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var activityViewModel: ActivityViewModel
    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.activity_main)

        activityViewModel = ViewModelProviders.of(this).get(ActivityViewModel::class.java)
        observeGetPosts()

        buttonOneClickListener()
    }

    private fun observeGetPosts() {
        activityViewModel.simpleLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> viewOneLoading()
                Status.SUCCESS -> viewOneSuccess(it.data)
                Status.ERROR -> viewOneError(it.error)
            }
        })
    }


    private fun buttonOneClickListener() {
        random.setOnClickListener {
            activityViewModel.getRandomRecipe()
        }
    }

    private fun viewOneLoading() {
        progressDialog = ProgressDialog(this)
        progressDialog!!.setTitle("Loading")
        progressDialog!!.setMessage("Wait while loading...")
        progressDialog!!.setCancelable(false) // disable dismiss by tapping outside of the dialog

        progressDialog!!.show()

    }

    private fun viewOneSuccess(data: Recipe?) {
        progressDialog!!.dismiss()
        data?.let {
            setupView(it)
        }
    }

    private fun viewOneError(error: Error?) {
        progressDialog!!.dismiss()

    }

    private fun setupView(recipe: Recipe) {
        recipe.run {
            name.text = title
            info.text = sourceUrl
            ready_in_minutes.text = readyInMinutes.toString()
            val uri = Uri.parse(image)
            image_recipe.setImageURI(uri)
            if(diets!!.isNotEmpty()) diets_text.text = diets!![0]
            if(dishTypes!!.isNotEmpty()) dish_types.text = dishTypes!![0]

            instruction_text.text = instructions
        }

    }

}
