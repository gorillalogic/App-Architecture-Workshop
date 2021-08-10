//
//  File.swift
//  
//
//  Created by Dylan Velez on 10/08/21.
//

import Foundation
import Models
import Services

@frozen public struct FavoritesState {
    public var list: [Pokemon] = []
    public var isLoading: Bool = false
    public init() { }
}

@frozen public enum FavoritesEvent {
    case fetchPokemon
    case fetchPokemonCompleted(pokemon: [Pokemon])
    case removeFromFavorites(id: Int)
}
