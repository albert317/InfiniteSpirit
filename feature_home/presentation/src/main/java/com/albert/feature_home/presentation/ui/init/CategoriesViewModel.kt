package com.albert.feature_home.presentation.ui.init

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albert.feature_home.domain.CategoryModel
import com.albert.feature_home.usecase.category.GetCategoriesUseCase
import com.albert.feature_home.usecase.category.SaveCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val saveCategoryUseCase: SaveCategoryUseCase,
) : ViewModel() {

    private val _uiStateCategory = MutableStateFlow<CategoryUiState>(CategoryUiState.Loading)
    val uiStateCategory: StateFlow<CategoryUiState> = _uiStateCategory

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().catch { }.collect { categories ->
                _uiStateCategory.value = CategoryUiState.Success(categories)
            }
        }
    }

    fun saveCategory() {
        viewModelScope.launch {
            saveCategoryUseCase(
                CategoryModel(
                    id = System.currentTimeMillis().toString(),
                    name = "Categoria",
                    description = "gdfg",
                    photo = "gdfgdfg",
                    timeRegister = System.currentTimeMillis().toString(),
                    timeUpdate = System.currentTimeMillis().toString(),
                )
            )
        }
    }

}

sealed interface CategoryUiState {
    object Loading : CategoryUiState
    data class Error(val throwable: Throwable) : CategoryUiState
    data class Success(val categories: List<CategoryModel>) : CategoryUiState
}