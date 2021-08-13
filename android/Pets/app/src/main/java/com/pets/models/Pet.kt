package com.pets.models

data class Pet(val id: Int,
               val name: String,
               val breed: String,
               val sex: String,
               val age: Int,
               val color: String,
               val weight: Int,
               val petDescription: String,
               val petImage: String,
               val vaccines: ArrayList<String>,
               val owner: Owner)