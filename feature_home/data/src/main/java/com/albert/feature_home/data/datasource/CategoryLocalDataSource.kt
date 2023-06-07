package com.albert.feature_home.data.datasource

import com.albert.feature_home.domain.CategoryModel
import kotlinx.coroutines.flow.Flow

interface CategoryDataSource {
    val categories: Flow<List<CategoryModel>>
    suspend fun isEmpty(): Boolean
    fun findById(id: String): Flow<CategoryModel>
    suspend fun save(categoryModel: CategoryModel): String?
}