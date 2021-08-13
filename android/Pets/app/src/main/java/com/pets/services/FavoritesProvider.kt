package com.pets.services

import android.content.Context
import android.content.SharedPreferences
import com.pets.models.Pet
import com.pets.services.Interfaces.FavoriteService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FavoritesProvider(val context: Context): FavoriteService {
    private val file = "com.gorillalogic.architecture.favorites"
    private val sharedPrefs: SharedPreferences by lazy {
        context.getSharedPreferences(file, Context.MODE_PRIVATE)
    }

    override fun fetchFavorites(): MutableSet<Int> {
        var ids = sharedPrefs.getStringSet("IDS", null)?.toMutableSet()
        var intIds = ids?.map { it.toInt()  }?.toMutableList()
        return intIds?.toMutableSet() ?: mutableSetOf()
    }

    override suspend fun fetchList(): List<Pet> {
        val ids = fetchFavorites()
        val petList = ArrayList<Pet>()
        val service = ServiceBuilder.buildService(FavoriteService::class.java)
        val list = service.fetchList()
        for (id in ids) {
            list.first { it.id == id }.let {
                petList.add(it)
            }
        }
        return petList
    }

    override fun addToFavorites(id: Int) {
        val ids = fetchFavorites()
        ids.add(id)
        val editor = sharedPrefs.edit()
        editor.putStringSet("IDS", ids.map { it.toString() }.toSet())
        editor.apply()
    }

    override fun removeFromFavorites(id: Int) {
        val ids = fetchFavorites()
        ids.remove(id)
        val editor = sharedPrefs.edit()
        editor.putStringSet("IDS", ids.map { it.toString() }.toSet())
        editor.apply()
    }
}