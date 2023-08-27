package com.albert.feature_home.presentation.ui.init

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.albert.feature_home.domain.CategoryModel
import com.albert.feature_home.domain.DrinkModel
import com.albert.feature_home.domain.IngredientModel
import com.albert.feature_home.presentation.BuildConfig
import com.albert.feature_home.presentation.R
import com.albert.feature_home.presentation.nav.FeatureScreen

@Composable
fun HomeScreen(navController: NavHostController) {
    val drinksViewModel = hiltViewModel<DrinksViewModel>()
    val categoriesViewModel = hiltViewModel<CategoriesViewModel>()
    val searchViewModel = hiltViewModel<SearchViewModel>()

    val uiStateDrinks = drinksViewModel.uiStateDrinks
    val uiStateCategories = categoriesViewModel.uiStateCategory
    val uiSearchState = searchViewModel.uiSearchState

    Content(
        uiStateDrinks.value.drinks,
        uiStateCategories.value.categories,
        uiSearchState.value.ingredients,
        { host -> navController.navigate(host) }) { drinksViewModel.saveIngredientsOfModel() }
}

@Composable
fun Content(
    drinks: List<DrinkModel> = emptyList(),
    categories: List<CategoryModel> = emptyList(),
    ingredients: List<IngredientModel> = emptyList(),
    onClickDrink: (String) -> Unit = {},
    onSaveProcessFirebase: () -> Unit = {},
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            SearchBarHome(
                ingredients,
                drinks
            )
            PopularList(
                drinks,
                categories,
                onClickDrink
            )
        }
        if (BuildConfig.DEBUG) {
            FloatingActionButton(
                onClick = { onSaveProcessFirebase() },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarHome(
    ingredients: List<IngredientModel> = emptyList(),
    drinks: List<DrinkModel> = emptyList(),
) {
    val ctx = LocalContext.current
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    fun onSearch(name: String) {
        Toast.makeText(ctx, "Search $name", Toast.LENGTH_SHORT).show()
        active = false
    }
    SearchBar(
        query = query,
        onQueryChange = { query = it },
        onSearch = { onSearch(query) },
        active = active,
        onActiveChange = { active = it },
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(4.dp),
        placeholder = { Text(text = "Buscar") },
        trailingIcon = {
            IconButton(onClick = { onSearch(query) }, enabled = query.isNotEmpty()) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search")
            }
        }) {
        if (query.isNotEmpty()) {
            val filteredIngredients = ingredients.filter { it.name.contains(query, true) }
            LazyColumn {
                items(filteredIngredients) { ingredient ->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            active = false
                            query = ingredient.name
                        }) {
                        Text(
                            text = "🧺 ${ingredient.name} - ${ingredient.id}",
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                }
                val filteredDrinks = drinks.filter { it.name.contains(query, true) }
                items(filteredDrinks) { drink ->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            active = false
                            query = drink.name
                        }) {
                        Text(
                            text = "🍸 ${drink.name} - ${drink.id}",
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun CategoryList(categories: List<CategoryModel>) {
    LazyRow { items(categories) { obj -> CategoryItem(obj) } }
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
fun PopularList(
    drinks: List<DrinkModel>,
    categories: List<CategoryModel>,
    onClickDrink: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        CategoryList(categories)
        Drinks(drinks, onClickDrink)
    }
}

@Composable
fun Drinks(drinks: List<DrinkModel>, onClickDrink: (String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        content = {
            items(drinks) { obj ->
                Drink2Item(onClickDrink, obj)
            }
        }
    )
}

@Composable
fun Drink2Item(onClickDrink: (String) -> Unit, drinkModel: DrinkModel) {
    val host = FeatureScreen.DetailScreen.withArgs(
        mapOf(Pair(FeatureScreen.ID_DRINK, drinkModel.id))
    )
    Card(
        Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                onClickDrink(host)
            }, shape = RoundedCornerShape(8.dp)
    ) {
        Box(modifier = Modifier.wrapContentHeight()) {
            val imagePainter = painterResource(R.drawable.back_ground_preparation)
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(drinkModel.photo).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp),
                placeholder = imagePainter
            )
            Box(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(listOf(Color.Transparent, Color.Black))
                    )
                    .align(Alignment.BottomCenter)
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = drinkModel.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}

@Composable
fun ItemTag(color: Color, icon: ImageVector) {
    Box(
        modifier = Modifier
            .size(16.dp)
            .background(color, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    val drink = DrinkModel(
        "2",
        "name",
        "description",
        "Perú",
        "",
        "",
        ""
    )
    Content(
        drinks = listOf(drink, drink, drink, drink, drink),
        categories = emptyList(),
    )
}