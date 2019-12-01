package com.example.foodformula.ApiConnection.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Steps(
    @SerializedName("number")
    var number:Int = 0,
    @SerializedName("step")
    var step: String? = null,
    @SerializedName("ingredients")
    var ingredients: List<Ingredient>? = null,
    @SerializedName("equipment")
    var equipment: List<Equipment>? = null
): Parcelable