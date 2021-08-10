//
//  File.swift
//  
//
//  Created by Dylan Velez on 10/08/21.
//

import Foundation
import Combine
import Models

public protocol FavoriteService {
    func fetchFavorites() -> Set<Int>
    func fetchPokemon() -> AnyPublisher<[Pokemon], Error>
    func addToFavorites(id: Int)
    func removeFromFavorites(id: Int)
}
