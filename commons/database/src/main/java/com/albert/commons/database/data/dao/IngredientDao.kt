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
        SELECT d.id, d.idDrink, d.idIngredient, i.name, i.type, d.quantity, d.timeRegister, d.timeUpdate
        FROM DrinkIngredientEntity d INNER JOIN  IngredientEntity i ON d.idIngredient = i.id
        """
    )
    fun getIngredientsForDrink(): Flow<List<IngredientWithQuantity>>

    @Query(
        """SELECT DrinkIngredientEntity.id AS id, DrinkIngredientEntity.idDrink AS idDrink, IngredientEntity.id AS idIngredient, IngredientEntity.name  AS name, IngredientEntity.type AS type, DrinkIngredientEntity.quantity AS quantity, DrinkIngredientEntity.timeRegister AS timeRegister, DrinkIngredientEntity.timeUpdate AS timeUpdate
        FROM IngredientEntity
        INNER JOIN DrinkIngredientEntity ON IngredientEntity.id = DrinkIngredientEntity.idIngredient 
    """
    )
    suspend fun getIngredientsForDrinkSimple(): List<IngredientWithQuantity>

    @Query("SELECT * from IngredientEntity")
    fun getIngredients(): Flow<List<IngredientEntity>>

    @Query("SELECT * from IngredientEntity")
    fun getOnlyIngredients(): List<IngredientEntity>

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