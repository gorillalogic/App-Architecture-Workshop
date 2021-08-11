//
//  File.swift
//  
//
//  Created by Dylan Velez on 10/08/21.
//

import Foundation
import Combine
import Models

final class FavoritesProvider: FavoriteService {
    private var listEndpoint = "https://learning-challenge.herokuapp.com/pets"
    
    public func fetchFavorites() -> Set<Int> {
        let storedIds = UserDefaults.standard.array(forKey: "com.gorillalogic.architecture.favorites") as? [Int]
        return Set(storedIds ?? [])
    }
    
    public func fetchList() -> AnyPublisher<Pets, Error> {
        let storedIds = UserDefaults.standard.array(forKey: "com.gorillalogic.architecture.favorites") as? [Int]
        return (storedIds ?? []).publisher
            .flatMap { self.getPet(id: $0) }
            .collect()
            .eraseToAnyPublisher()
    }
    
    public func addToFavorites(id: Int) {
        let storedIds = UserDefaults.standard.array(forKey: "com.gorillalogic.architecture.favorites") as? [Int]
        var setOfIds = Set(storedIds ?? [])
        setOfIds.insert(id)
        UserDefaults.standard.set(Array(setOfIds), forKey: "com.gorillalogic.architecture.favorites")
    }
    
    public func removeFromFavorites(id: Int) {
        let storedIds = UserDefaults.standard.array(forKey: "com.gorillalogic.architecture.favorites") as? [Int]
        var setOfIds = Set(storedIds ?? [])
        setOfIds.remove(id)
        UserDefaults.standard.set(Array(setOfIds), forKey: "com.gorillalogic.architecture.favorites")
    }
    
    private func getPet(id: Int) -> AnyPublisher<Pet, Error> {
        guard let url = URL(string: listEndpoint) else {
            return Empty().eraseToAnyPublisher()
        }
        
        return URLSession.shared.dataTaskPublisher(for: url)
            .map(\.data)
            .decode(type: Pets.self, decoder: JSONDecoder())
            .compactMap { $0.first { pet in
                pet.id == id
            } }
            .eraseToAnyPublisher()
    }
}
