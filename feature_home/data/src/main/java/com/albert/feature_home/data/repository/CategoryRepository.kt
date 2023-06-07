package com.albert.feature_home.data.repository

import com.albert.feature_home.data.datasource.CategoryLocalDataSource
import com.albert.feature_home.domain.CategoryModel
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryDataSource: CategoryLocalDataSource,
) {
    fun getCategories() = categoryDataSource.categories
    suspend fun isEmpty() = categoryDataSource.isEmpty()
    fun getCategory(id: String) = categoryDataSource.findById(id)
    suspend fun saveCategory(categoryModel: CategoryModel) = categoryDataSource.save(categoryModel)
}