//
//  File.swift
//  
//
//  Created by Dylan Velez on 10/08/21.
//

import Foundation

public class ServiceLocator {
    public static var localService: DataService = LocalDataProvider()
    public static var httpService: DataService = HttpDataProvider()
    public static var favoriteService: FavoriteService = FavoritesProvider()
    public static var logService: LoggingService = LogProvider()
}
