package com.example.navigation.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PropertyViewModel(application: Application): AndroidViewModel(application) {
    lateinit var  readAllData : Flow<List<Property>>
    lateinit var getShuffledProperties : Flow<List<Property>>
    private val repository: PropertyRepository



    init {
        val scope = CoroutineScope(SupervisorJob())
        val propertyDao = PropertyDatabase.getDatabase(application,scope).propertyDao()
        repository = PropertyRepository(propertyDao)
        viewModelScope.launch {
            readAllData = repository.getProperties()
            getShuffledProperties = repository.getShuffledProjects()
        }
    }
    suspend fun addProperty(property: Property){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProperty(property)
        }
    }

    fun updateProperty(property: Property){
        viewModelScope.launch {
            repository.updateProperty(property.houseId)
        }

    }

}