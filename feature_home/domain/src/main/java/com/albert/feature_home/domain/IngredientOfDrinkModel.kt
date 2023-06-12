package com.albert.feature_home.domain

data class IngredientOfDrinkModel(
    val id: String,
    val idDrink: String,
    val idIngredient: String,
    val name: String,
    val type: String,
    val quantity: String,
    val timeRegister:String,
    val timeUpdate:String
)