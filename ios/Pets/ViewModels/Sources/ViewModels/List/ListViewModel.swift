//
//  File.swift
//  
//
//  Created by Dylan Velez on 11/08/21.
//

import Foundation
import Architecture
import Models
import Domain
import Combine

public final class ListViewModel: ViewModel {
    public typealias Event = ListEvent
    public typealias ListStore = Store<ListState, ListEvent, ListReducer>
    
    public struct ViewState {
        public var list: Pets = []
        public var favorites: Pets = []
        public var isLoading: Bool = false
    }
    
    @Published private(set) public var state: ViewState = ViewState()
    public var store: ListStore
    private var stateCancellable: AnyCancellable?
    
    public init(store: ListStore) {
        self.store = store
        stateCancellable = store.$state.sink(receiveValue: stateChanged)
    }
    
    public func stateChanged(newState: ListState) {
        if !newState.list.isEmpty {
            DispatchQueue.main.async {
                let oldList = Set(self.state.list).union(Set(newState.list))
                self.state.list = Array(oldList).sorted { $0.id < $1.id }
            }
        }
        self.state.isLoading = newState.isLoading
        self.state.favorites = newState.favorites
    }
}
