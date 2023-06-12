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

    override suspend fun categoriesSimple(): List<CategoryModel> =
        categoryDao.getCategoriesSimple().map { it.toModel() }

    override suspend fun isEmpty(): Boolean =
        categoryDao.count() == 0

    override fun findById(id: String): Flow<CategoryModel> =
        categoryDao.findById(id).map { it.toModel() }

    override suspend fun save(categoryModel: CategoryModel): String? {
        return try {
            categoryDao.add(categoryModel.toEntity())
            null
        } catch (e: Exception) {
            e.message
        }
    }

    override suspend fun update(categoryModel: CategoryModel): String? {
        return try {
            categoryDao.update(categoryModel.toEntity())
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
        photo = photo,
        timeRegister = timeRegister,
        timeUpdate = timeUpdate,
    )
}

fun CategoryModel.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        name = name,
        description = description,
        photo = photo,
        timeRegister = timeRegister,
        timeUpdate = timeUpdate,
    )
}

