package com.albert.commons.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.albert.commons.database.data.entity.PreparationStepEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PreparationDao {

    @Query("SELECT * FROM PreparationStepEntity WHERE idDrink=:idDrink")
    fun getPreparations(idDrink: String): Flow<List<PreparationStepEntity>>

    @Query("SELECT * FROM PreparationStepEntity")
    suspend fun getPreparationsSimple(): List<PreparationStepEntity>

    @Query("SELECT COUNT(id) FROM PreparationStepEntity")
    suspend fun count(): Int

    @Insert
    suspend fun add(preparationEntity: PreparationStepEntity)

    @Update
    suspend fun update(preparationEntity: PreparationStepEntity)
}