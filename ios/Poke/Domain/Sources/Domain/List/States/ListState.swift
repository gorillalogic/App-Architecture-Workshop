//
//  File.swift
//  
//
//  Created by Dylan Velez on 10/08/21.
//

import Foundation
import Models
import Services

@frozen public struct ListState {
    public var list: [Pokemon] = []
    public var isLoading = false
    
    public init() {
        
    }
}

@frozen public enum ListEvent {
    case fetchPokemon
    case fetchPokemonCompleted(pokemon: [Pokemon])
}
