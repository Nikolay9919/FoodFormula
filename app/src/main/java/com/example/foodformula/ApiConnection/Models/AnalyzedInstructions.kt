package com.example.foodformula.ApiConnection.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AnalyzedInstructions(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("steps")
    var steps: List<Steps>? = null
) : Parcelable

