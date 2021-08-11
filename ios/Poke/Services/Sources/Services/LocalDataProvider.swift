//
//  File.swift
//  
//
//  Created by Dylan Velez on 10/08/21.
//

import Foundation
import Combine
import Models

final class LocalDataProvider: DataService {
    
    public enum LocalError: Error {
        case fileNotFound
        case unknown
    }
    func getList() -> AnyPublisher<Pets, Error> {
        guard let path = Bundle.module.url(forResource: "List", withExtension: "json") else {
            return Fail(error: LocalError.fileNotFound).eraseToAnyPublisher()
        }
        
        return Just(path)
            .tryMap { try Data(contentsOf: $0) }
            .decode(type: Pets.self, decoder: JSONDecoder())
            .eraseToAnyPublisher()
    }
    
    func getItem(id: Int) -> AnyPublisher<Pet, Error> {
        return getList()
            .flatMap { $0.publisher }
            .first { $0.id == id }
            .eraseToAnyPublisher()
    }
}
