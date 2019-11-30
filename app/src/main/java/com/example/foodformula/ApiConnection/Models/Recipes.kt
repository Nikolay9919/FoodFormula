package com.example.foodformula.ApiConnection.Models

import com.google.gson.annotations.SerializedName

class Recipes {
    @SerializedName("recipes")
    var recipes: List<Recipe?>? = null

}
