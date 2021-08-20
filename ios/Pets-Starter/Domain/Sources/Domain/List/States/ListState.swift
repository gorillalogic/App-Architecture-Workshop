//
//  File.swift
//  
//
//  Created by Dylan Velez on 10/08/21.
//

import Foundation
import Models
import Services

public struct ListState {
    public var list: Pets = []
    public var favorites: Pets = []
    public var isLoading = false
    
    public init() {
        
    }
}

public enum ListEvent {
    case fetchList
    case fetchFavorites
    case fetchListCompleted(pets: Pets)
    case fetchFavoritesCompleted(pets: Pets)
    case addToFavorites(id: Int)
    case removeFromFavorites(id: Int)
}
