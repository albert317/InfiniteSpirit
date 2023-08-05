package com.albert.feature_home.domain

data class DrinkIngredientModel(
    val id: String,
    val idDrink: String,
    val idIngredient: String,
    val quantity: String,
    val timeRegister:String =System.currentTimeMillis().toString(),
    val timeUpdate:String=System.currentTimeMillis().toString(),
)