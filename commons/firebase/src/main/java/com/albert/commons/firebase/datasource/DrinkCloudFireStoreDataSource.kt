package com.albert.commons.firebase.datasource

import com.albert.commons.firebase.remote.RemoteDrink
import com.albert.feature_home.data.datasource.DrinkRemoteDataSource
import com.albert.feature_home.domain.DrinkModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DrinkCloudFireStoreDataSource @Inject constructor(private val fireStore: FirebaseFirestore) :
    DrinkRemoteDataSource {

    override suspend fun drinks(): List<DrinkModel> = try {
        val snapshot = fireStore.collection("Drink").get().await()
        val drinks = snapshot.documents.mapNotNull {
            it.toObject(RemoteDrink::class.java)?.toModel(it.id)
        }
        drinks
    } catch (e: Exception) {
        emptyList()
    }

    override suspend fun findById(id: String): DrinkModel? {
        return try {
            val snapshot = fireStore.collection("Drink").document(id).get().await()
            snapshot.toObject(RemoteDrink::class.java)?.toModel(snapshot.id)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun save(drinkModel: DrinkModel): String? {
        return try {
            fireStore.collection("Drink").document().set(drinkModel.toRemote()).await()
            null
        } catch (e: Exception) {
            e.message
        }
    }
}

private fun RemoteDrink.toModel(id: String): DrinkModel {
    return DrinkModel(
        id = id,
        name = name ?: "",
        description = description ?: "",
        origin = origin ?: "",
        photo = photo ?: "",
        timeRegister = timeRegister ?: "",
        timeUpdate = timeUpdate ?: ""
    )
}

private fun DrinkModel.toRemote(): RemoteDrink {
    return RemoteDrink(
        name = name,
        description = description,
        origin = origin,
        photo = photo,
        timeRegister = timeRegister,
        timeUpdate = timeUpdate
    )
}