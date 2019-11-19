package com.example.foodformula.ApiConnection.Models

import com.google.gson.annotations.SerializedName

data class WinePairing(
    @SerializedName("pairedWines")
    var pairedWines: Array<String>?,
    @SerializedName("pairingText")
    var pairingText: String?,
    @SerializedName("productMatches")
    var productMatches: Array<String>?
)