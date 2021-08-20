package com.pets.domain.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pets.architecture.common.Depending
import com.pets.architecture.reducers.Reducing
import com.pets.domain.list.ListEvent
import com.pets.models.Pet
import com.pets.services.ServiceLocator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
