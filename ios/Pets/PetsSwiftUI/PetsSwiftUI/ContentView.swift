//
//  ContentView.swift
//  PetsSwiftUI
//
//  Created by Dylan Velez on 11/08/21.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        TabView {
            
            NavigationView {
                ListView(viewModel: .init(store: .init(initialState: .init(), reducer: .init())))
            }
            .tabItem {
                VStack {
                    Image(systemName: "list.star")
                    Text("Pets")
                }
            }
            
            NavigationView {
                FavoritesView()
            }
            .tabItem {
                VStack {
                    Image(systemName: "heart.fill")
                    Text("Favorites")
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
