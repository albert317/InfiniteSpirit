package com.albert.commons.database.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DrinkEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val origin: String,
    val photo: String,
    val timeRegister: String,
    val timeUpdate: String,
)