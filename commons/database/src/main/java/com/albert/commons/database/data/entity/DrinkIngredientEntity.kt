package com.albert.commons.database.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DrinkIngredientEntity(
    @PrimaryKey
    val id: String,
    val idDrink: String,
    val idIngredient: String,
    val quantity: String,
    val timeRegister: String,
    val timeUpdate: String,
)