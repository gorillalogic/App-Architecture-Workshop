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
    func log(action: LoggingAction) {
        os_log("User %{public}@ logged in", action.logDescription)
    }
}
