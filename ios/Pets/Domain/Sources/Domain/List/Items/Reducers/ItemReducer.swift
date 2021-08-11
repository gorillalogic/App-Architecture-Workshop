//
//  File.swift
//  
//
//  Created by Dylan Velez on 10/08/21.
//

import Foundation
import Architecture
import Combine
import Services

public class ItemReducer: Reducing, Depending {
    public typealias State = ItemState
    public typealias Event = ItemEvent
    
    public struct Dependencies {
        var favoriteService: FavoriteService = ServiceLocator().favoriteService
    }
    
    let dependencies = Dependencies()
    
    public init() { }
    
    private func sideEffect(event: Event) -> Effect<Event> {
        switch event {
        case .fetchIsFavorite(let id):
            return Just(.fetchIsFavoriteCompleted(dependencies.favoriteService.fetchFavorites().contains(id))).eraseToAnyPublisher()
        case .addToFavorites(let id):
            dependencies.favoriteService.addToFavorites(id: id)
        case .removeFromFavorites(let id):
            dependencies.favoriteService.removeFromFavorites(id: id)
        case .fetchIsFavoriteCompleted:
            break
        }
        return nil
    }
    
    public func reduce(state: inout State, forEvent event: Event) -> Effect<Event> {
        switch event {
        case .addToFavorites:
            state.isFavorite = true
        case .removeFromFavorites:
            state.isFavorite = false
        case .fetchIsFavoriteCompleted(let isFavorite):
            state.isFavorite = isFavorite
        case .fetchIsFavorite:
            break
        }
        
        return sideEffect(event: event)
    }
}
