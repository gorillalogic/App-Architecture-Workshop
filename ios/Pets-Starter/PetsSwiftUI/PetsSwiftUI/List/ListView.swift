//
//  ListView.swift
//  PetsSwiftUI
//
//  Created by Dylan Velez on 11/08/21.
//

import SwiftUI
import Architecture
import ViewModels
import Domain
import Services

struct ListView: View, ViewConfigurable {
    @ObservedObject var viewModel: ListViewModel
    var body: some View {
        ScrollView {
            LazyVStack {
                ForEach(viewModel.state.list) { pet in
                    CardView(model: pet, isFavorite: viewModel.state.favorites.contains(pet),
                             onClickLike: {
                                if viewModel.state.favorites.contains(pet) {
                                    viewModel.dispatch(event: .removeFromFavorites(id: pet.id))
                                } else {
                                    viewModel.dispatch(event: .addToFavorites(id: pet.id))
                                }
                             })
                        .frame(height: 300)
                }
                .padding()
            }
            if viewModel.state.isLoading {
                ProgressView()
            }
        }
        .navigationTitle("Pets")
        .onAppear(perform: fetchData)
    }
    
    private func fetchData() {
        viewModel.dispatch(event: .fetchList)
        viewModel.dispatch(event: .fetchFavorites)
    }
}

struct ListView_Previews: PreviewProvider {
    static var previews: some View {
        
        let listVM = ListViewModel(
            store: .init(
                initialState: .init(),
                reducer: .init(
                    listService: ServiceLocator.localService,
                    favoriteService: ServiceLocator.favoriteService)
            )
        )
        
        ListView(viewModel: listVM)
    }
}
