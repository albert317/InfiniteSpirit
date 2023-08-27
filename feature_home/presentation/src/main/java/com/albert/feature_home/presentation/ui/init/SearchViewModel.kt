package com.albert.feature_home.presentation.ui.init

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albert.feature_home.domain.IngredientModel
import com.albert.feature_home.usecase.ingredient.GetIngredientsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getIngredientsUseCase: GetIngredientsUseCase,
) : ViewModel() {
    private val _uiSearchState: MutableState<SearchUiState> = mutableStateOf(SearchUiState())
    val uiSearchState: State<SearchUiState> = _uiSearchState

    init {
        getIngredients()
    }

    private fun getIngredients() {
        viewModelScope.launch {
            getIngredientsUseCase().catch { }.collect { ingredients ->
                _uiSearchState.value =
                    _uiSearchState.value.copy(ingredients = ingredients, loading = false)
            }
        }
    }
}

data class SearchUiState(
    val loading: Boolean = false,
    val throwable: Throwable? = null,
    val ingredients: List<IngredientModel> = emptyList(),
)