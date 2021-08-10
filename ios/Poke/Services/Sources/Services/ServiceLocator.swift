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
    public lazy var mockedList: DataService = MockedPokeService()
    public lazy var favoriteService: FavoriteService = FavoritesProvider()
    
    public init() { }
}
