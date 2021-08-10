//
//  File.swift
//  
//
//  Created by Dylan Velez on 10/08/21.
//

import Foundation

public struct PokeType: Codable, Equatable {
    public let name: String
    
    private enum OuterKeys: String, CodingKey {
        case type
    }
    
    private enum CodingKeys: String, CodingKey {
        case name
    }
    
    public init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: OuterKeys.self)
        let type = try container.nestedContainer(keyedBy: CodingKeys.self, forKey: .type)
        name = try type.decode(String.self, forKey: .name)
    }
    
    public init(name: String) {
        self.name = name
    }
}
