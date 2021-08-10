//
//  File.swift
//  
//
//  Created by Dylan Velez on 10/08/21.
//

/// Defines an instance which can dispatch events
public protocol Dispatching {
    associatedtype Event
    func dispatch(event: Event)
}
