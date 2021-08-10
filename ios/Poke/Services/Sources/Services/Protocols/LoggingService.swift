//
//  File.swift
//  
//
//  Created by Dylan Velez on 10/08/21.
//
import Foundation
import Combine

public protocol LoggerAction {
    var logDescription: String { get }
}

public protocol LoggingService {
    func log(action: LoggerAction)
}

extension LoggingService {
    func log(action: LoggerAction) -> AnyPublisher<Void, Never> {
        log(action: action)
        return Empty().eraseToAnyPublisher()
    }
}
