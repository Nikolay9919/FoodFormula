package com.example.foodformula.ApiConnection.Models

import android.content.ClipData
import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("vegetarian")
    var vegetarian: Boolean?,
    @SerializedName("preparationMinutes")
    var preparationMinutes: Int?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("aggregateLikes")
    var aggregateLikes: Int?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("readyInMinutes")
    var readyInMinutes: Int?,
    @SerializedName("servings")
    var servings: Int?,
    @SerializedName("image")
    var imageUrl: String?,
    @SerializedName("cuisines")
    var cuisines: Array<String>?,
    @SerializedName("dishTypes")
    var dishTypes: Array<String>?,
    @SerializedName("diets")
    var diets: Array<String>?,
    @SerializedName("occasions")
    var occasions: Array<String>?,
    @SerializedName("extendedIngredients")
    var winePairing: Array<WinePairing>?,
    @SerializedName("extendedIngredients")
    var extendedIngredients: List<Ingredient>?,
    @SerializedName("items")
    var items: List<ClipData.Item?>?
)