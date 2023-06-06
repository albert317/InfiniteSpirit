package com.albert.feature_home.usecase.category

import com.albert.feature_home.data.repository.CategoryRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val repository: CategoryRepository) {
    operator fun invoke() = repository.getCategories()
}