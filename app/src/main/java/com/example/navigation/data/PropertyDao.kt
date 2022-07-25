package com.example.navigation.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PropertyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProperty(property: Property)

    @Query("UPDATE property_table SET isFavorite = NOT isFavorite WHERE houseId=:id")
    suspend fun updateProperty(id: Int)

    @Query("SELECT * FROM property_table")
    fun getProperties(): Flow<List<Property>>

    @Query("SELECT * FROM property_table ORDER BY RANDOM() LIMIT 5")
    fun getShuffledProperties(): Flow<List<Property>>
}