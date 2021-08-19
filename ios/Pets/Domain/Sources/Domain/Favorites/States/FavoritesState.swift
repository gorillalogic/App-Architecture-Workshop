//
//  File.swift
//  
//
//  Created by Dylan Velez on 10/08/21.
//

import Foundation
import Models
import Services

public struct FavoritesState {
    public var list: Pets = []
    public var isLoading: Bool = false
    public init() { }
}

public enum FavoritesEvent {
    case fetchList
    case fetchListCompleted(pets: Pets)
    case removeFromFavorites(id: Int)
}

extension FavoritesEvent: LoggingAction {
    public var logDescription: String {
        switch self {
        case .fetchList:
            return "Action - Fetching favorites list"
        case .fetchListCompleted:
            return "Action - Completed Fetching favorites"
        case .removeFromFavorites:
            return "Action - Removed item from favorites"
        }
    }
}
