package com.albert.commons.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.albert.commons.database.data.entity.IngredientEntity
import com.albert.commons.database.data.entity.IngredientWithQuantity
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {
    @Query(
        """
        SELECT DrinkIngredientEntity.id AS id, DrinkIngredientEntity.idDrink AS idDrink, IngredientEntity.id AS idIngredient, IngredientEntity.name, IngredientEntity.type, DrinkIngredientEntity.quantity, DrinkIngredientEntity.timeRegister, DrinkIngredientEntity.timeUpdate
        FROM IngredientEntity
        INNER JOIN DrinkIngredientEntity ON IngredientEntity.id = DrinkIngredientEntity.idIngredient
        WHERE DrinkIngredientEntity.idDrink = :idDrink
    """
    )
    fun getIngredientsForDrink(idDrink: String): Flow<List<IngredientWithQuantity>>

    @Query(
        """
        SELECT DrinkIngredientEntity.id AS id, DrinkIngredientEntity.idDrink AS idDrink, IngredientEntity.id AS idIngredient, IngredientEntity.name, IngredientEntity.type, DrinkIngredientEntity.quantity, DrinkIngredientEntity.timeRegister, DrinkIngredientEntity.timeUpdate
        FROM IngredientEntity
        INNER JOIN DrinkIngredientEntity ON IngredientEntity.id = DrinkIngredientEntity.idIngredient
    """
    )
    suspend fun getIngredientsForDrinkSimple(): List<IngredientWithQuantity>


    @Query("SELECT * FROM IngredientEntity WHERE id=:id")
    fun findById(id: String): Flow<IngredientEntity>

    @Query("SELECT * FROM IngredientEntity WHERE id=:id")
    suspend fun findByIdSimple(id: String): IngredientEntity


    @Insert
    suspend fun addIngredient(ingredientEntity: IngredientEntity)

    @Query("SELECT COUNT(id) FROM IngredientEntity")
    suspend fun ingredientCount(): Int


    @Update
    suspend fun updateIngredient(ingredientEntity: IngredientEntity)


}