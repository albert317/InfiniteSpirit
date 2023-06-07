package com.albert.feature_home.data.datasource

import com.albert.feature_home.domain.CategoryModel
import kotlinx.coroutines.flow.Flow

interface CategoryRemoteDataSource {
    val categories: Flow<List<CategoryModel>>
    fun findById(id: String): Flow<CategoryModel>
    suspend fun save(categoryModel: CategoryModel): String?
}