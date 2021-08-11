//
//  File.swift
//  
//
//  Created by Dylan Velez on 11/08/21.
//

import Foundation
import Combine

public protocol LoggingAction {
    var logDescription: String { get }
}

public protocol LoggingService {
    func log(action: LoggingAction)
}
