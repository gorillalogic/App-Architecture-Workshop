package com.pets.services

import android.content.Context
import com.pets.services.Interfaces.DataService
import com.pets.services.Interfaces.FavoriteService
import com.pets.services.Interfaces.LoggingService

object ServiceLocator {
    val favoriteService: FavoriteService by lazy { FavoritesProvider() }
    val dataService: DataService by lazy { ServiceBuilder.buildService(DataService::class.java) }
    val localDataService: DataService by lazy { LocalDataProvider() }
    val logService: LoggingService by lazy { LogProvider() }
}