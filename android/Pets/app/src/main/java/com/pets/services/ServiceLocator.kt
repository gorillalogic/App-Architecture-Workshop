package com.pets.services

import android.content.Context
import com.pets.services.Interfaces.DataService
import com.pets.services.Interfaces.FavoriteService
import com.pets.services.Interfaces.LoggingService

class ServiceLocator(val context: Context) {
    val favoriteService: FavoriteService by lazy { FavoritesProvider(context = context) }
    val dataService: DataService by lazy { ServiceBuilder.buildService(DataService::class.java) }
    val localDataService: DataService by lazy { LocalDataProvider() }
    val logService: LoggingService by lazy { LogProvider() }
}