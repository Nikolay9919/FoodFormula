package com.example.foodformula.ApiConnection.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ingredient(
    @SerializedName("vegetarian")
    var vegetarian: Boolean?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("aisle")
    var aisle: String?,
    @SerializedName("consitency")
    var consitency: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("original")
    var original: String?,
    @SerializedName("amount")
    var amount: Float?,
    @SerializedName("unit")
    var unit: String?,
    @SerializedName("image")
    var image: String?

): Parcelable