package com.albert.commons.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.albert.commons.database.data.entity.ManagerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ManagerDao {
    @Query("SELECT * FROM ManagerEntity")
    fun getManagers(): Flow<List<ManagerEntity>>

    @Query("SELECT * FROM ManagerEntity")
    suspend fun getManagersSimple(): List<ManagerEntity>

    @Query("SELECT * FROM ManagerEntity WHERE id=:id")
    fun findById(id: String): Flow<ManagerEntity>

    @Query("SELECT * FROM ManagerEntity WHERE id=:id")
    suspend fun findByIdSimple(id: String): ManagerEntity

    @Query("SELECT COUNT(id) FROM ManagerEntity")
    suspend fun managerCount(): Int

    @Insert
    suspend fun addManager(managerEntity: ManagerEntity)

    @Update
    suspend fun updateManager(managerEntity: ManagerEntity)
}