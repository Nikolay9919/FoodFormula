package com.example.foodformula.ApiConnection.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Measures(
    @SerializedName("us")
    var us: US? = null,
    @SerializedName("metric")
    var metric: Metric? = null

): Parcelable