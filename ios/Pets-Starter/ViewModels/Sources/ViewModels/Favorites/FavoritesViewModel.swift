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
}
