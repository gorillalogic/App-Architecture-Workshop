//
//  File.swift
//  
//
//  Created by Dylan Velez on 10/08/21.
//

import Foundation
import Combine
import Models

public protocol DataService {
    func getList() -> AnyPublisher<Pets, Error>
}
