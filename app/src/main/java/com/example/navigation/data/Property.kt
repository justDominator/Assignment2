package com.example.navigation.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "property_table")
data class Property(
    @PrimaryKey(autoGenerate = true)
    val houseId :Int = 0,
    var houseName: String = "",
    var seller : String = "",
    var size : String = "",
    var location : String = "",
    var price : String = "",
    var isFavorite : Boolean = false,
)
