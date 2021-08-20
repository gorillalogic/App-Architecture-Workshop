//
//  File.swift
//  
//
//  Created by Dylan Velez on 11/08/21.
//

import Foundation
public struct Owner: Codable {
    let name, phone, email, address: String
    
    public init(name: String,  phone: String, email: String, address: String) {
        self.name = name
        self.phone = phone
        self.email = email
        self.address = address
    }
}

extension Owner: Hashable { }
