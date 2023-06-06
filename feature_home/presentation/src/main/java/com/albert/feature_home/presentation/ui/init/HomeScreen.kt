package com.albert.feature_home.presentation.ui.init

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.albert.feature_home.domain.CategoryModel
import com.albert.feature_home.domain.DrinkModel
import com.albert.feature_home.presentation.R

@Composable
fun HomeScreen() {
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val drinksViewModel = hiltViewModel<DrinksViewModel>()
    val uiStateDrinks by produceState<DrinksUiState>(
        initialValue = DrinksUiState.Loading, key1 = lifecycle, key2 = drinksViewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            drinksViewModel.uiStateDrinks.collect { drinks -> value = drinks }
        }
    }

    val categoryViewModel = hiltViewModel<CategoriesViewModel>()
    val categoryUiState by produceState<CategoryUiState>(
        initialValue = CategoryUiState.Loading,
        key1 = lifecycle,
        key2 = categoryViewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            categoryViewModel.uiStateCategory.collect { categories -> value = categories }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            SearchBarHome()
            when (uiStateDrinks) {
                is DrinksUiState.Error -> {}
                is DrinksUiState.Loading -> {}
                is DrinksUiState.Success -> {
                    if (categoryUiState is CategoryUiState.Success) {
                        PopularList(
                            (uiStateDrinks as DrinksUiState.Success).drinks,
                            (categoryUiState as CategoryUiState.Success).categories
                        )
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = { drinksViewModel.saveDrink() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Agregar")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarHome() {
    val ctx = LocalContext.current
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    fun onSearch(name: String) {
        Toast.makeText(ctx, "Search $name", Toast.LENGTH_SHORT).show()
        active = false
    }
    SearchBar(query = query,
        onQueryChange = { query = it },
        onSearch = {
            onSearch(query)
        },
        active = active,
        onActiveChange = { active = it },
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(4.dp),
        placeholder = { Text(text = "Search") },
        trailingIcon = {
            IconButton(onClick = { onSearch(query) }, enabled = query.isNotEmpty()) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search")
            }
        }) {
        if (query.isNotEmpty()) {
            val filteredCountries = countries.filter { it.second.contains(query, true) }
            LazyColumn {
                items(filteredCountries) { (flag, name) ->
                    Text(text = "$flag $name", modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            Toast
                                .makeText(ctx, name, Toast.LENGTH_SHORT)
                                .show()
                            active = false
                            query = name
                        })
                }
            }
        }
    }
}


@Composable
fun CategoryList(categories: List<CategoryModel>) {
    LazyRow {
        items(categories) { obj ->
            CategoryItem(obj)
        }
    }
}


@Composable
fun CategoryItem(categoryModel: CategoryModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(8.dp)
    ) {
        val image = painterResource(R.drawable.lasagna)

        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        Text(
            text = categoryModel.name,
            style = MaterialTheme.typography.bodyLarge,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}


@Composable
fun PopularList(drinks: List<DrinkModel>, categories: List<CategoryModel>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            CategoryList(categories)
        }
        items(drinks) { obj ->
            Drink2Item(obj)
        }
    }
}

@Composable
fun Drink2Item(drinkModel: DrinkModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 0.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.lasagna),
                contentDescription = "Imagen de sfsdfsdf",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )

            Spacer(Modifier.width(16.dp))

            Column {
                Text(text = "bebida.nombr")
                Text(text = "bebida.ingredientes.joinToString()")
            }
        }
    }
}

val countries = listOf(
    "🇵🇪" to "Peru",
    "🇨🇱" to "Chile",
    "🇦🇷" to "Argentina",
    "🇧🇷" to "Brazil",
    "🇨🇴" to "Colombia",
    "🇪🇨" to "Ecuador",
    "🇵🇾" to "Paraguay",
    "🇺🇾" to "Uruguay",
    "🇧🇴" to "Bolivia",
    "🇻🇪" to "Venezuela",
    "🇬🇾" to "Guyana",
    "🇸🇷" to "Suriname",
    "🇬🇫" to "French Guiana",
    "🇵🇦" to "Panama",
    "🇨🇷" to "Costa Rica",
    "🇳🇮" to "Nicaragua",
    "🇭🇳" to "Honduras",
    "🇬🇹" to "Guatemala",
    "🇧🇿" to "Belize",
    "🇲🇽" to "Mexico",
    "🇨🇺" to "Cuba",
    "🇩🇴" to "Dominican Republic",
    "🇭🇹" to "Haiti",
    // Agrega más países según sea necesario...
)

