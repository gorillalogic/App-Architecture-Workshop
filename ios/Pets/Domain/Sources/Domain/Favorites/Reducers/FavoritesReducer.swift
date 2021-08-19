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

public struct FavoritesReducer: Reducing, Depending {
    public typealias State = FavoritesState
    public typealias Event = FavoritesEvent
    
    public struct Dependencies {
        public var service: FavoriteService = ServiceLocator.favoriteService
        public var logService: LoggingService = ServiceLocator.logService
        public init(service: FavoriteService, logService: LoggingService) {
            self.service = service
            self.logService = logService
        }
    }
    
    public var dependencies: Dependencies
    
    public init(service: FavoriteService, logService: LoggingService) {
        self.dependencies = Dependencies(service: service, logService: logService)
    }
    
    public func reduce(state: inout FavoritesState, forEvent event: FavoritesEvent) -> Effect<Event> {
        
        switch event {
        case .removeFromFavorites(let id):
            state.list.removeAll(where: { $0.id == id })
        case .fetchListCompleted(let list):
            state.list = list
            state.isLoading = false
        case .fetchList:
            state.isLoading = true
            break
        }
        
        return sideEffect(event: event)
    }
    
    private func sideEffect(event: Event) -> Effect<Event> {
        dependencies.logService.log(action: event)
        switch event {
        case .fetchList:
            return dependencies.service.fetchList()
                .map(FavoritesEvent.fetchListCompleted)
                .catch { error in
                    return Just(.fetchListCompleted(pets: []))
                }
                .eraseToAnyPublisher()
            
        case .removeFromFavorites(let id):
            dependencies.service.removeFromFavorites(id: id)
        case .fetchListCompleted:
            break
        }
        return nil
    }
}
