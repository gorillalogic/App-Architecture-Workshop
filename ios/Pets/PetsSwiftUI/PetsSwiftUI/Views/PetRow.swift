//
//  PetRow.swift
//  PetsSwiftUI
//
//  Created by Dylan Velez on 11/08/21.
//

import SwiftUI
import Models
import URLImage

struct PetRow: View {
    var model: Pet
    var onClickLike: () -> Void
    var body: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 10)
                .fill(Color.white)
                .shadow(radius: 10)
            HStack {
                URLImage(URL(string: model.petImage)!) {
                    Image("pawprint")
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                } inProgress: { _ in
                    Image("pawprint")
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                } failure: { _,_  in
                    
                }
                content: { image in
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                }
                Spacer()
                    .frame(width: 20)
                Text(model.name)
                    .font(.largeTitle)
                    .foregroundColor(.purple)
                Spacer()
                Button(action: onClickLike) {
                    Image(systemName: "heart.fill")
                        .imageScale(.large)
                        .foregroundColor(.purple)
                }
                .buttonStyle(PlainButtonStyle())
                Spacer()
                    .frame(width: 20)
            }
        }
        .frame(height: 100)
    }
}

struct PetRow_Previews: PreviewProvider {
    static var previews: some View {
        let model = Pet(id: 1, name: "Scottie", breed: "Golden", sex: "Male", age: 5, color: "Brown", weight: 16, petDescription: "No Description", petImage: "https://learning-challenge.herokuapp.com/images/pet-1.jpg", vaccines: [], owner: .init(name: "Dylan", phone: "", email: "", address: ""))
        PetRow(model: model, onClickLike: {})
    }
}
