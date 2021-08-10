//
//  File.swift
//  
//
//  Created by Dylan Velez on 10/08/21.
//

import Foundation

public struct Sprite: Codable, Equatable {
    public let front: String
    
    private enum CodingKeys: String, CodingKey {
        case front = "front_default"
    }
    
    public init(front: String) {
        self.front = front
    }
}

