package com.albert.commons.database.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ManagerEntity(
    @PrimaryKey
    val id:String,
    val name: String,
    val isUpdateRequired:Boolean,
    val timeRegister: String,
    val timeUpdate: String,
)