package com.example.foodformula.ApiConnection.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Metric(
    @SerializedName("amount")
    var amount: Double,
    @SerializedName("unitShort")
    var unitShort: String?,
    @SerializedName("unitLong")
    var unitLong: String?

) : Parcelable
