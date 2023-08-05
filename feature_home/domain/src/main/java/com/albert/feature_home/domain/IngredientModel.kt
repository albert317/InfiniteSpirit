package com.albert.feature_home.domain

data class IngredientModel(
    val id: String,
    val name: String,
    val type: String,
    val timeRegister: String = System.currentTimeMillis().toString(),
    val timeUpdate: String = System.currentTimeMillis().toString(),
)