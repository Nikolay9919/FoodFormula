package com.example.foodformula.ViewModels

import androidx.lifecycle.ViewModel
import com.example.foodformula.ApiConnection.Models.Recipe


class RecipeViewModel : ViewModel() {
    private var recipe: Recipe? = null
    fun init(recipe: Recipe) {
        this.recipe = recipe
    }

}