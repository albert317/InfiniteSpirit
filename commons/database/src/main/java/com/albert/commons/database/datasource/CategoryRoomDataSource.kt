package com.albert.commons.database.datasource

import com.albert.commons.database.data.dao.CategoryDao
import com.albert.commons.database.data.entity.CategoryEntity
import com.albert.feature_home.data.datasource.CategoryLocalDataSource
import com.albert.feature_home.domain.CategoryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRoomDataSource @Inject constructor(private val categoryDao: CategoryDao) :
    CategoryLocalDataSource {
    override val categories: Flow<List<CategoryModel>> =
        categoryDao.getCategories().map { categories -> categories.map { it.toModel() } }

    override suspend fun isEmpty(): Boolean =
        categoryDao.categoryCount() == 0

    override fun findById(id: String): Flow<CategoryModel> =
        categoryDao.findById(id).map { it.toModel() }

    override suspend fun save(categoryModel: CategoryModel): String? {
        return try {
            categoryDao.addCategory(categoryModel.toEntity())
            null
        } catch (e: Exception) {
            e.message
        }
    }
}

fun CategoryEntity.toModel(): CategoryModel {
    return CategoryModel(
        id = id,
        name = name,
        description = description,
        photo = photo
    )
}

fun CategoryModel.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        name = name,
        description = description,
        photo = photo
    )
}

