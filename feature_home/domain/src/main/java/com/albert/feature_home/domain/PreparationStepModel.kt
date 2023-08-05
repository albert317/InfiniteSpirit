package com.albert.feature_home.domain

data class PreparationStepModel(
    val id: String,
    var idDrink: String,
    val order: Int,
    val description: String,
    val timeRegister: String,
    val timeUpdate: String,
)