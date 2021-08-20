//
//  File.swift
//  
//
//  Created by Dylan Velez on 10/08/21.
//

import Foundation
import Combine

fileprivate typealias StoreType = ObservableObject & Dispatching

/// Generic Store which dispatches events and it's current state to the reducers
public final class Store<State, Event, Reducer: Reducing>: StoreType where Reducer.State == State, Reducer.Event == Event {
    
}
