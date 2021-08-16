package com.pets.services.Interfaces

import com.pets.models.Pet
import retrofit2.http.GET

interface FavoriteService {
    fun fetchFavorites(): Set<Int>
    @GET(".")
    suspend fun fetchList(): List<Pet>
    fun addToFavorites(id: Int)
    fun removeFromFavorites(id: Int)
}