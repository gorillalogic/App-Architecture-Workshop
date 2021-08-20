package com.pets.models

import com.google.gson.annotations.SerializedName

data class Pet(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("breed")
    val breed: String,
    @SerializedName("sex")
    val sex: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("color")
    val color: String,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("description")
    val petDescription: String,
    @SerializedName("pet_image")
    val petImage: String,
    @SerializedName("vaccines")
    val vaccines: ArrayList<String>,
    @SerializedName("owner")
    val owner: Owner
    )