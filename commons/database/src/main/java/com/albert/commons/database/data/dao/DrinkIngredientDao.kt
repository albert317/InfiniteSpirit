package com.albert.commons.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.albert.commons.database.data.entity.DrinkIngredientEntity

@Dao
interface DrinkIngredientDao {

    @Query("SELECT * FROM DrinkIngredientEntity")
    suspend fun getDrinkIngredient(): List<DrinkIngredientEntity>

    @Query("SELECT * FROM DrinkIngredientEntity WHERE id=:id")
    suspend fun find(id: String): DrinkIngredientEntity?

    @Insert
    suspend fun add(drinkIngredientEntity: DrinkIngredientEntity)

    @Query("SELECT COUNT(id) FROM DrinkIngredientEntity")
    suspend fun count(): Int

    @Update
    suspend fun update(drinkIngredientEntity: DrinkIngredientEntity)

}