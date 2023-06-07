package com.albert.feature_home.domain

data class DrinkModel(
    val id: String,
    val name: String,
    val description: String,
    val origin: String,
    val photo: String,
    val timeRegister:String,
    val timeUpdate:String
)