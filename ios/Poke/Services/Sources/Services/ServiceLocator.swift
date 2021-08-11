//
//  File.swift
//  
//
//  Created by Dylan Velez on 10/08/21.
//

import Foundation

public class ServiceLocator {
    public lazy var localService: DataService = LocalDataProvider()
    public lazy var httpService: DataService = HttpDataProvider()
    public lazy var favoriteService: FavoriteService = FavoritesProvider()
    public lazy var logService: LoggingService = LogProvider()
    
    public init() { }
}
