package com.albert.commons.database.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.albert.commons.database.data.entity.DrinkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DrinkDao {
    @Query("SELECT * from DrinkEntity ORDER BY timeRegister DESC")
    fun getDrinks(): Flow<List<DrinkEntity>>

    @Query("SELECT * FROM DrinkEntity WHERE id=:id")
    fun findById(id: String): Flow<DrinkEntity>

    @Query("SELECT COUNT(id) FROM DrinkEntity")
    suspend fun drinkCount(): Int

    @Insert
    suspend fun addDrink(drinkEntity: DrinkEntity)

    @Update
    suspend fun updateDrink(drinkEntity: DrinkEntity)

    @Delete
    suspend fun deleteDrink(drinkEntity: DrinkEntity)

    @Query("SELECT * FROM DrinkEntity WHERE id=:id")
    suspend fun findByIdSimple(id: String): DrinkEntity?

    @Query("SELECT * from DrinkEntity")
    suspend fun getDrinksSimple(): List<DrinkEntity>

}