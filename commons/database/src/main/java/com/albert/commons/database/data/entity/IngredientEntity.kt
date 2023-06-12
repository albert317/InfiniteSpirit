package com.albert.commons.database.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IngredientEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val type: String,
    val timeRegister:String,
    val timeUpdate:String,
)