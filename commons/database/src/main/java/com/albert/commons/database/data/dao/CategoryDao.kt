package com.albert.commons.database.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.albert.commons.database.data.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * from CategoryEntity")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM CategoryEntity WHERE id=:id")
    fun findById(id: String): Flow<CategoryEntity>

    @Query("SELECT COUNT(id) FROM CategoryEntity")
    suspend fun categoryCount(): Int

    @Insert
    suspend fun addCategory(categoryEntity: CategoryEntity)

    @Update
    suspend fun updateCategory(categoryEntity: CategoryEntity)

    @Delete
    suspend fun deleteCategory(categoryEntity: CategoryEntity)
}