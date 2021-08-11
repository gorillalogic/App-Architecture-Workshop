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
    public var list: Pets = []
    public var isLoading = false
    
    public init() {
        
    }
}

@frozen public enum ListEvent {
    case fetchList
    case fetchListCompleted(pets: Pets)
}
