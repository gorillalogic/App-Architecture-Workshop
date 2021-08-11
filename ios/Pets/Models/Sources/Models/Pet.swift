//
//  File.swift
//  
//
//  Created by Dylan Velez on 11/08/21.
//

import Foundation

public typealias Pets = [Pet]
public struct Pet: Codable {
    public let id: Int
    public let name: String
    public let breed: String
    public let sex: String
    public let age: Int
    public let color: String
    public let weight: Int
    public let petDescription: String
    public let petImage: String
    public let vaccines: [String]
    public let owner: Owner

    enum CodingKeys: String, CodingKey {
        case id, name, breed, sex, age, color, weight
        case petDescription = "description"
        case petImage = "pet_image"
        case vaccines, owner
    }
    
    public init(id: Int, name: String, breed: String, sex: String, age: Int, color: String, weight: Int, petDescription: String, petImage: String, vaccines: [String], owner: Owner) {
        self.id = id
        self.name =  name
        self.breed = breed
        self.sex = sex
        self.age = age
        self.color = color
        self.weight = weight
        self.petDescription = petDescription
        self.petImage = petImage
        self.vaccines = vaccines
        self.owner = owner
    }
}

extension Pet: Identifiable { }
extension Pet: Hashable { }
