package com.albert.commons.database.data.entity

data class IngredientWithQuantity (
    val id: String,
    val idDrink: String,
    val idIngredient: String,
    val name: String,
    val type: String,
    val quantity: String,
    val timeRegister:String,
    val timeUpdate:String
)