package com.albert.feature_home.data.datasource

import com.albert.feature_home.domain.CategoryModel

interface CategoryRemoteDataSource {
    suspend fun categories(): List<CategoryModel>
    suspend fun findById(id: String): CategoryModel?
    suspend fun save(categoryModel: CategoryModel): String?
}