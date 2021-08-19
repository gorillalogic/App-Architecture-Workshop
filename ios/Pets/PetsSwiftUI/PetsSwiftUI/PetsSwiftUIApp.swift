//
//  PetsSwiftUIApp.swift
//  PetsSwiftUI
//
//  Created by Dylan Velez on 11/08/21.
//

import SwiftUI
import URLImage
import URLImageStore

@main
struct PetsSwiftUIApp: App {
    var body: some Scene {
        let urlImageService = URLImageService(fileStore: URLImageFileStore(),
                                          inMemoryStore: URLImageInMemoryStore())

        return WindowGroup {
            ContentView()
                .environment(\.urlImageService, urlImageService)
        }
    }
}
