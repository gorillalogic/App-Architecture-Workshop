//
//  File.swift
//  
//
//  Created by Dylan Velez on 10/08/21.
//
import Foundation
import Architecture
import Services
import Combine

public class FavoritesReducer: Reducing, Depending {
    public typealias State = FavoritesState
    public typealias Event = FavoritesEvent
    
    public struct Dependencies {
        public var service: FavoriteService = ServiceLocator().favoriteService
    }
    
    public var dependencies = Dependencies()
    
    public init() { }
    
    public func reduce(state: inout FavoritesState, forEvent event: FavoritesEvent) -> Effect<Event> {
        
        switch event {
        case .removeFromFavorites(let id):
            state.list.removeAll(where: { $0.id == id })
        case .fetchPokemonCompleted(let list):
            state.list = list
            state.isLoading = false
        case .fetchPokemon:
            state.isLoading = true
            break
        }
        
        return sideEffect(event: event)
    }
    
    private func sideEffect(event: Event) -> Effect<Event> {
        switch event {
        case .fetchPokemon:
            return dependencies.service.fetchPokemon()
                .map { FavoritesEvent.fetchPokemonCompleted(pokemon: $0) }
                .catch { error in
                    return Just(.fetchPokemonCompleted(pokemon: []))
                }
                .eraseToAnyPublisher()
            
        case .removeFromFavorites(let id):
            dependencies.service.removeFromFavorites(id: id)
        case .fetchPokemonCompleted:
            break
        }
        return nil
    }
}
