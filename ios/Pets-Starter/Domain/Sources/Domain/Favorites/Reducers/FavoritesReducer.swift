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
}
