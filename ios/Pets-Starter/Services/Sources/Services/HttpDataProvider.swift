//
//  File.swift
//  
//
//  Created by Dylan Velez on 10/08/21.
//

import Foundation
import Combine
import Models

final class HttpDataProvider: DataService {
    private var listEndpoint = "https://learning-challenge.herokuapp.com/pets"
    
    func getList() -> AnyPublisher<Pets, Error> {
        
        guard let url = URL(string: listEndpoint) else {
            return Empty().eraseToAnyPublisher()
        }
        return URLSession.shared.dataTaskPublisher(for: url)
            .map(\.data)
            .decode(type: Pets.self, decoder: JSONDecoder())
            .eraseToAnyPublisher()
    }
}
