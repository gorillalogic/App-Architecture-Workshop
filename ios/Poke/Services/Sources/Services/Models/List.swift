//
//  File.swift
//  
//
//  Created by Dylan Velez on 10/08/21.
//

import Foundation

struct NamedResource: Codable {
    let name: String
    let url: String
}

struct List: Codable {
    let count: Int
    let next: String?
    let previous: String?
    let results: [NamedResource]
}
