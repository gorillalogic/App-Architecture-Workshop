//
//  FavoritesView.swift
//  PetsSwiftUI
//
//  Created by Dylan Velez on 11/08/21.
//

import SwiftUI
import Architecture
import ViewModels

struct FavoritesView: View, ViewConfigurable {
    @ObservedObject var  viewModel: FavoritesViewModel
    var body: some View {
        ScrollView {
            LazyVStack {
                ForEach(viewModel.state.list) { pet in
                    PetRow(model: pet, onClickLike: {
                        viewModel.dispatch(event: .removeFromFavorites(id: pet.id))
                    })
                        .frame(height: 100)
                }
                .padding()
            }
            if viewModel.state.isLoading {
                ProgressView()
            }
        }
        .navigationTitle("Favorites")
        .onAppear(perform: fetchData)
    }
    
    private func fetchData() {
        viewModel.dispatch(event: .fetchList)
    }
}

struct FavoritesView_Previews: PreviewProvider {
    static var previews: some View {
        FavoritesView(viewModel: .init(store: .init(initialState: .init(), reducer: .init())))
    }
}
