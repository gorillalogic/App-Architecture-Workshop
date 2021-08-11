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

struct ListView: View, ViewConfigurable {
    @ObservedObject var viewModel: ListViewModel
    var body: some View {
        ScrollView {
            ForEach(viewModel.state.list) { pet in
                CardView(model: pet)
                    .frame(height: 300)
            }
            .padding()
            if viewModel.state.isLoading {
                ProgressView()
            }
        }
        .navigationTitle("Pets")
        .onAppear(perform: fetchData)
    }
    
    private func fetchData() {
        viewModel.dispatch(event: .fetchList)
    }
}

struct ListView_Previews: PreviewProvider {
    static var previews: some View {
        ListView(viewModel: .init(store: .init(initialState: .init(), reducer: .init())))
    }
}
