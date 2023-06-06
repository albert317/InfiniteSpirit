package com.albert.commons.database.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val photo: String,
)