//
//  ContentView.swift
//  PetsSwiftUI
//
//  Created by Dylan Velez on 11/08/21.
//

import SwiftUI
import Services
import ViewModels

struct ContentView: View {
    let listVM = ListViewModel(
        store: .init(
            initialState: .init(),
            reducer: .init(
                listService: ServiceLocator.httpService,
                favoriteService: ServiceLocator.favoriteService)
        )
    )
    
    let favoritesVM = FavoritesViewModel(
        store: .init(
            initialState: .init(),
            reducer: .init(
                service: ServiceLocator.favoriteService,
                logService: ServiceLocator.logService)
        )
    )
    
    var body: some View {
        TabView {
            NavigationView {
                ListView(viewModel: listVM)
            }
            .tabItem {
                VStack {
                    Image(systemName: "list.star")
                    Text("Pets")
                }
            }
            
            NavigationView {
                FavoritesView(viewModel: favoritesVM)
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
