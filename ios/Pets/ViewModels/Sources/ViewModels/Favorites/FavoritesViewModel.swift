//
//  File.swift
//  
//
//  Created by Dylan Velez on 11/08/21.
//

import Foundation
import Architecture
import Domain
import Combine
import Models

public final class FavoritesViewModel: ViewModel {
    public typealias Event = FavoritesEvent
    public typealias FavoriteStore = Store<FavoritesState, FavoritesEvent,  FavoritesReducer>
    
    public struct ViewState {
        public var list: Pets = []
        public var isLoading = false
    }
    
    public var store: FavoriteStore
    private var stateCancellable: AnyCancellable?
    @Published private(set) public var state: ViewState = ViewState()
    
    public init(store: FavoriteStore) {
        self.store = store
        self.stateCancellable = store.$state.sink(receiveValue: stateChanged)
    }
    
    public func stateChanged(newState: FavoritesState) {
        self.state.list = newState.list.sorted { $0.id < $1.id }
        self.state.isLoading = newState.isLoading
    }
}
