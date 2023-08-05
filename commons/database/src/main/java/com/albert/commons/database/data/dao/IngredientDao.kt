package com.albert.commons.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.albert.commons.database.data.entity.IngredientEntity
import com.albert.commons.database.data.entity.IngredientWithQuantity
import com.albert.feature_home.domain.IngredientModel
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {
    @Query(
        """
        SELECT DrinkIngredientEntity.id AS id, DrinkIngredientEntity.idDrink AS idDrink, DrinkIngredientEntity.idIngredient AS idIngredient, "IngredientEntity.name" AS name, "IngredientEntity.type" AS type, DrinkIngredientEntity.quantity AS quantity, DrinkIngredientEntity.timeRegister AS timeRegister, DrinkIngredientEntity.timeUpdate AS timeUpdate
        FROM DrinkIngredientEntity
    """
    )
    fun getIngredientsForDrink(): Flow<List<IngredientWithQuantity>>

    @Query(
        """
        SELECT DrinkIngredientEntity.id AS id, DrinkIngredientEntity.idDrink AS idDrink, IngredientEntity.id AS idIngredient, IngredientEntity.name, IngredientEntity.type, DrinkIngredientEntity.quantity, DrinkIngredientEntity.timeRegister, DrinkIngredientEntity.timeUpdate
        FROM IngredientEntity
        INNER JOIN DrinkIngredientEntity ON IngredientEntity.id = DrinkIngredientEntity.idIngredient
    """
    )
    suspend fun getIngredientsForDrinkSimple(): List<IngredientWithQuantity>

    @Query("SELECT * from IngredientEntity")
    fun getOnlyIngredients():List<IngredientEntity>

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