package com.albert.commons.firebase.datasource

import com.albert.commons.firebase.remote.RemoteIngredient
import com.albert.feature_home.data.datasource.IngredientRemoteDataSource
import com.albert.feature_home.domain.IngredientModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class IngredientCloudFirebaseStoreDataSource @Inject constructor(private val fireStore: FirebaseFirestore) :
    IngredientRemoteDataSource {
    override suspend fun ingredients(): List<IngredientModel> = try {
        val snapshot = fireStore.collection("Ingredient").get().await()
        val ingredients = snapshot.documents.mapNotNull {
            it.toObject(RemoteIngredient::class.java)?.toModel(it.id)
        }
        ingredients
    } catch (e: Exception) {
        emptyList()
    }


    override suspend fun findById(id: String): IngredientModel? {
        return try {
            val snapshot = fireStore.collection("Ingredient").document(id).get().await()
            snapshot.toObject(RemoteIngredient::class.java)?.toModel(snapshot.id)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun save(ingredient: IngredientModel): String? {
        return try {
            fireStore.collection("Ingredient").document().set(ingredient.toRemote()).await()
            null
        } catch (e: Exception) {
            e.message
        }
    }
}


private fun RemoteIngredient.toModel(id: String) = IngredientModel(
    id = id,
    name = name ?: "",
    type = type ?: "",
    timeRegister = timeRegister ?: "",
    timeUpdate = timeUpdate ?: ""
)

private fun IngredientModel.toRemote() = RemoteIngredient(
    name = name,
    type = type,
    timeRegister = timeRegister,
    timeUpdate = timeUpdate
)
