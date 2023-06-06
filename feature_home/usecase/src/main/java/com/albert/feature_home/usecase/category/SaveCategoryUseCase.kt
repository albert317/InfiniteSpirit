package com.albert.feature_home.usecase.category

import com.albert.feature_home.data.repository.CategoryRepository
import com.albert.feature_home.domain.CategoryModel
import javax.inject.Inject

class SaveCategoryUseCase @Inject constructor(private val repository: CategoryRepository) {
    suspend operator fun invoke(categoryModel: CategoryModel) =
        repository.saveCategory(categoryModel)
}