package com.example.navigation.data

import kotlinx.coroutines.flow.Flow

class PropertyRepository(private val propertyDao: PropertyDao){

    suspend fun getProperties(): Flow<List<Property>> = propertyDao.getProperties()
    suspend fun addProperty(property: Property){
        propertyDao.addProperty(property)
    }
    suspend fun updateProperty(id:Int) {
        propertyDao.updateProperty(id)
    }
    suspend fun getShuffledProjects() : Flow<List<Property>> =
        propertyDao.getShuffledProperties()
}