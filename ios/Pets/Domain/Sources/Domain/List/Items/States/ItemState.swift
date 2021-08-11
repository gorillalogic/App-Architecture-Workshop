//
//  File.swift
//  
//
//  Created by Dylan Velez on 10/08/21.
//

import Foundation

@frozen public struct ItemState {
    public var isFavorite: Bool = false
    public init() { }
}

@frozen public enum ItemEvent {
    case addToFavorites(id: Int)
    case removeFromFavorites(id: Int)
    case fetchIsFavorite(id: Int)
    case fetchIsFavoriteCompleted(Bool)
}

