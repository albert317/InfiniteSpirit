package com.albert.commons.firebase.datasource

import com.albert.commons.firebase.remote.RemoteDrinkIngredient
import com.albert.feature_home.data.datasource.DrinkIngredientRemoteDataSource
import com.albert.feature_home.domain.DrinkIngredientModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DrinkIngredientCloudFireStoreDataSource @Inject constructor(private val fireStore: FirebaseFirestore) :
    DrinkIngredientRemoteDataSource {
    override suspend fun drinksIngredient(): List<DrinkIngredientModel> = try {
        val snapshot = fireStore.collection("DrinkIngredient").get().await()
        val ingredientsOfDrink = snapshot.documents.mapNotNull {
            it.toObject(RemoteDrinkIngredient::class.java)?.toModel(it.id)
        }
        ingredientsOfDrink
    } catch (e: Exception) {
        emptyList()
    }

    override suspend fun findById(id: String): DrinkIngredientModel? = try {
        val snapshot = fireStore.collection("DrinkIngredient").document(id).get().await()
        snapshot.toObject(RemoteDrinkIngredient::class.java)?.toModel(snapshot.id)
    } catch (e: Exception) {
        null
    }

    override suspend fun save(drinkIngredientModel: DrinkIngredientModel): String? = try {
        val newDocument = fireStore.collection("DrinkIngredient").document()
        newDocument.set(drinkIngredientModel.toRemote()).await()
        newDocument.id
    } catch (e: Exception) {
        null
    }

    private fun RemoteDrinkIngredient.toModel(id: String) = DrinkIngredientModel(
        id = id,
        idDrink = idDrink ?: "",
        idIngredient = idIngredient ?: "",
        quantity = quantity ?: "",
        timeRegister = timeRegister ?: "",
        timeUpdate = timeUpdate ?: ""
    )

    private fun DrinkIngredientModel.toRemote() = RemoteDrinkIngredient(
        idDrink = idDrink,
        idIngredient = idIngredient,
        quantity = quantity,
        timeRegister = timeRegister,
        timeUpdate = timeUpdate
    )

}