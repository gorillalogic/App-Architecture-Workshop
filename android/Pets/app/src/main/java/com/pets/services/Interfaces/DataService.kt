package com.pets.services.Interfaces

import com.pets.models.Pet
import retrofit2.http.GET

interface DataService {
    @GET(".")
    suspend fun getList(): List<Pet>
}