package com.example.foodformula.UI

import android.app.ProgressDialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.foodformula.ApiConnection.Models.Recipe
import com.example.foodformula.ApiConnection.Status
import com.example.foodformula.R
import com.example.foodformula.ViewModels.ActivityViewModel
import kotlinx.android.synthetic.main.recipe_layout.*

class RecipeFragment : Fragment() {

    companion object {

        fun newInstance(): RecipeFragment {
            return RecipeFragment()
        }
    }

    private lateinit var activityViewModel: ActivityViewModel
    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewModel = ViewModelProviders.of(this).get(ActivityViewModel::class.java)
        observeGetPosts()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recipe_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        progressDialog = ProgressDialog(context)
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
            if (diets!!.isNotEmpty()) diets_text.text = diets!![0]
            if (dishTypes!!.isNotEmpty()) dish_types.text = dishTypes!![0]

            instruction_text.text = instructions
        }

    }
}