package com.albert.feature_home.domain

data class ManagerModel(
    val id: String,
    val name: String,
    val isUpdateRequired:Boolean,
    val timeRegister: String,
    val timeUpdate: String,
)