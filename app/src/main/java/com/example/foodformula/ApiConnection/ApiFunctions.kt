package com.example.foodformula.ApiConnection

import com.example.foodformula.ApiConnection.Models.Recipe
import com.example.foodformula.ApiConnection.Models.Recipes
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiFunctions {
    @GET("recipes/random")
    suspend fun getRandomRecipe(
    ): ResponseWrapperRecipes<Recipe>

    @GET("5dcc147154000059009c2104")
    suspend fun getUsersError(
        @Query("page") page: Int
    ): ResponseWrapperRecipes<Recipes>
}