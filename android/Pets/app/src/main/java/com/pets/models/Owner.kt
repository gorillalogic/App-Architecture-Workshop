package com.pets.models

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("address")
    val address: String)