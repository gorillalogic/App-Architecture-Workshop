package com.pets.services.Interfaces

import android.util.Log

interface LogAction {
    fun description(): String
}

interface LoggingService {
    fun log(action: LogAction) {
        Log.d("PETS", action.description())
    }
}