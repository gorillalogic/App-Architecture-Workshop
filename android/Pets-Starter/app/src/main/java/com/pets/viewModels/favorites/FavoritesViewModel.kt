package com.pets.viewModels.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.pets.architecture.stores.Store
import com.pets.architecture.viewModels.ViewModel
import com.pets.domain.favorites.FavoritesEvent
import com.pets.domain.favorites.FavoritesReducer
import com.pets.domain.favorites.FavoritesState
import com.pets.domain.favorites.FetchList
import com.pets.models.Pet