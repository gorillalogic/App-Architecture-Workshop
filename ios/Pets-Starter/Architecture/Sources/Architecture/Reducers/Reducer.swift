//
//  File.swift
//  
//
//  Created by Dylan Velez on 10/08/21.
//

import Foundation
import Combine

public typealias Effect<Event> = AnyPublisher<Event, Never>?

/// Defines a type which changes a given state for a specific event and returns a new side effect
public protocol Reducing {
    
}
