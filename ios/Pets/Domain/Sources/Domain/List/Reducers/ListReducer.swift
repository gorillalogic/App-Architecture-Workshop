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
        public var listService: DataService = ServiceLocator().httpService
    }
    
    public var dependencies = Dependencies()
    
    public init() { }
    
    public func reduce(state: inout State, forEvent event: Event) -> Effect<Event> {
        switch event {
        case .fetchList:
            state.list = []
            state.isLoading = true
        case .fetchListCompleted(let newPokemon):
            state.list = newPokemon
            state.isLoading = false
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
        case .fetchListCompleted:
            return nil
        }
    }
}