package com.albert.feature_home.presentation.ui.init

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albert.feature_home.domain.CategoryModel
import com.albert.feature_home.usecase.category.GetCategoriesUseCase
import com.albert.feature_home.usecase.category.SaveCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val saveCategoryUseCase: SaveCategoryUseCase,
) : ViewModel() {

    private val _uiStateCategory: MutableState<CategoryUiState> = mutableStateOf(CategoryUiState())
    val uiStateCategory: State<CategoryUiState> = _uiStateCategory

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().catch { }.collect { categories ->
                _uiStateCategory.value =
                    _uiStateCategory.value.copy(categories = categories, loading = false)
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

data class CategoryUiState(
    val loading: Boolean = false,
    val throwable: Throwable? = null,
    val categories: List<CategoryModel> = emptyList(),
)