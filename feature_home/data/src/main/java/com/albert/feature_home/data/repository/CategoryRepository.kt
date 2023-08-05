package com.albert.feature_home.data.repository

import com.albert.feature_home.data.datasource.CategoryLocalDataSource
import com.albert.feature_home.data.datasource.CategoryRemoteDataSource
import com.albert.feature_home.domain.CategoryModel
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryLocalDataSource: CategoryLocalDataSource,
    private val categoryRemoteDataSource: CategoryRemoteDataSource,
) {
    fun getCategories() = categoryLocalDataSource.categories
    suspend fun isEmpty() = categoryLocalDataSource.isEmpty()
    fun getCategory(id: String) = categoryLocalDataSource.findById(id)
    suspend fun saveCategory(categoryModel: CategoryModel) =
        categoryLocalDataSource.save(categoryModel)

    suspend fun updateCategory(categoryModel: CategoryModel) =
        categoryLocalDataSource.update(categoryModel)

    suspend fun requestCategories() {
        val remoteCategories = categoryRemoteDataSource.categories()
        if (isEmpty()) {
            remoteCategories.map { saveCategory(it) }
        } else {
            val localCategories = categoryLocalDataSource.categoriesSimple()
            remoteCategories.map { category ->
                val localCategory = localCategories.find { it.id == category.id }
                if (localCategory == null) {
                    saveCategory(category)
                }
                if (localCategory != null && localCategory.timeUpdate < category.timeUpdate) {
                    updateCategory(category)
                }
            }
        }
    }
}