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
    
    private var listEndpoint = "https://pokeapi.co/api/v2/pokemon/"
    
    func getPokemonList() -> AnyPublisher<[Pokemon], Error> {
        
        guard let url = URL(string: listEndpoint) else {
            return Empty().eraseToAnyPublisher()
        }
        
        let publisher = URLSession.shared.dataTaskPublisher(for: url)
            .map(\.data)
            .decode(type: List.self, decoder: JSONDecoder())
            .map { list -> [NamedResource] in
                self.listEndpoint = list.next ?? ""
                return list.results
            }
            .flatMap { results in
                results.publisher
                    .map(\.url)
                    .flatMap {
                        URLSession.shared.dataTaskPublisher(for: URL(string: $0)!)
                            .map(\.data)
                            .decode(type: Pokemon.self, decoder: JSONDecoder())
                    }
            }
            .collect()
        return publisher.eraseToAnyPublisher()
    }
}
