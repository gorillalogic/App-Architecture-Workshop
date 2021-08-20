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

public struct ListReducer: Reducing, Depending {
    public typealias State = ListState
    public typealias Event = ListEvent
    
    public struct Dependencies {
        public var listService: DataService
        public var favoriteService: FavoriteService
        
        public init(listService: DataService,
                    favoriteService: FavoriteService) {
            self.listService = listService
            self.favoriteService = favoriteService
        }
    }
    
    public var dependencies: Dependencies
    
    public init(listService: DataService, favoriteService: FavoriteService) {
        self.dependencies = Dependencies(listService: listService, favoriteService: favoriteService)
    }
    
    public func reduce(state: inout State, forEvent event: Event) -> Effect<Event> {
        switch event {
        case .fetchList:
            state.list = []
            state.isLoading = true
        case .fetchListCompleted(let newPets):
            state.list = newPets
            state.isLoading = false
        case .fetchFavorites:
            state.favorites = []
        case .fetchFavoritesCompleted(let favorites):
            state.favorites =  favorites
        case .addToFavorites(let id):
            if let favorite = state.list.first(where: {$0.id == id}) {
                state.favorites.append(favorite)
            }
        case .removeFromFavorites(let id):
            state.favorites.removeAll { $0.id == id }
        }
        
        return sideEffect(event: event)
    }
    
    private func sideEffect(event: Event) -> Effect<Event> {
        switch event {
        case .fetchList:
            return dependencies.listService.getList()
                .map(Event.fetchListCompleted)
                .replaceError(with: .fetchListCompleted(pets: []))
                .eraseToAnyPublisher()
        case .fetchFavorites:
            return dependencies.favoriteService.fetchList()
                .map(Event.fetchFavoritesCompleted)
                .replaceError(with: Event.fetchFavoritesCompleted(pets: []))
                .eraseToAnyPublisher()
        case .addToFavorites(let id):
            dependencies.favoriteService.addToFavorites(id: id)
        case .removeFromFavorites(let id):
            dependencies.favoriteService.removeFromFavorites(id: id)
        case .fetchListCompleted, .fetchFavoritesCompleted:
            return nil
        }
        return nil
    }
}
