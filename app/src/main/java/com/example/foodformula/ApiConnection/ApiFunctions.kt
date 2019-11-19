package com.example.foodformula.ApiConnection

import com.example.foodformula.ApiConnection.Models.Recipe
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiFunctions {
    @GET("5dcc12d554000064009c20fc")
    suspend fun getUsers(
        @Query("page") page: Int
    ): ResponseWrapper<Recipe>

    @GET("5dcc147154000059009c2104")
    suspend fun getUsersError(
        @Query("page") page: Int
    ): ResponseWrapper<Recipe>
}