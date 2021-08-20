package com.pets.services

import com.google.gson.Gson
import com.pets.models.Pet
import com.pets.services.Interfaces.DataService

class LocalDataProvider: DataService {
    val listString =
        """
        [
          {
            "id": 1,
            "name": "Charlie",
            "breed": "Beagle",
            "sex": "Female",
            "age": 1,
            "color": "White with brown and black spots",
            "weight": 40,
            "description": "She's a very friendly dog, well-socialized to other dogs and humans by her breeder, and she would drag us to greet every human and dog she met.",
            "pet_image": "https://learning-challenge.herokuapp.com/images/pet-1.jpg",
            "vaccines": [
              "6-8 weeks",
              "10-12 weeks",
              "14-16 weeks"
            ],
            "owner": {
              "name": "Haroon Webster",
              "phone": "970-288-1530",
              "email": "h.webster@gl.com",
              "address": "Colorado, Boulder"
            }
          },
          {
            "id": 2,
            "name": "Aurora",
            "breed": "French Bulldog",
            "sex": "Female",
            "age": 7,
            "color": "Brown",
            "weight": 53,
            "description": "She's a very friendly dog, well-socialized to other dogs and humans by her breeder, and she would drag us to greet every human and dog she met.",
            "pet_image": "https://learning-challenge.herokuapp.com/images/pet-2.jpg",
            "vaccines": [
              "6-8 weeks",
              "10-12 weeks"
            ],
            "owner": {
              "name": "Sherry Raymond",
              "phone": "170-536-1054",
              "email": "s.raymond@gl.com",
              "address": "Colorado, Aspen"
            }
          }
        ]
        """
    override suspend fun getList(): List<Pet> {
        return Gson().fromJson(listString, Array<Pet>::class.java).toList()
    }
}