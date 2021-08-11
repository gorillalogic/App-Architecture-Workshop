//
//  File.swift
//  
//
//  Created by Dylan Velez on 11/08/21.
//

import Foundation
import Combine
import os

struct LogProvider: LoggingService {
    func log(action: LoggingAction) -> AnyPublisher<Void, Never> {
        os_log("User %{public}@ logged in", action.logDescription)
        return Empty().eraseToAnyPublisher()
    }
}
