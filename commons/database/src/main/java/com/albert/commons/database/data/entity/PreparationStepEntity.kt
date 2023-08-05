package com.albert.commons.database.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PreparationStepEntity(
    @PrimaryKey
    val id: String,
    val idDrink: String,
    val order: Int,
    val description: String,
    val timeRegister: String,
    val timeUpdate: String,
)