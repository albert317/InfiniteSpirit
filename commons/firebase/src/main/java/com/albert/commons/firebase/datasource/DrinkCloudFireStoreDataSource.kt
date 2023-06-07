package com.albert.commons.firebase.datasource

import android.util.Log
import com.albert.commons.firebase.data.RemoteDrink
import com.albert.feature_home.data.datasource.DrinkRemoteDataSource
import com.albert.feature_home.domain.DrinkModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DrinkCloudFireStoreDataSource @Inject constructor(private val fireStore: FirebaseFirestore) :
    DrinkRemoteDataSource {

    override suspend fun drinks(): List<DrinkModel> = withContext(Dispatchers.IO) {
        try {
            val snapshot = fireStore.collection("Drink").get().await()
            val drinks = snapshot.documents.mapNotNull {
                it.toObject(RemoteDrink::class.java)?.toModel(it.id)
            }
            drinks
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun findById(id: String): DrinkModel? = withContext(Dispatchers.IO) {
        try {
            val snapshot = fireStore.collection("Drink").document(id).get().await()
            snapshot.toObject(RemoteDrink::class.java)?.toModel(snapshot.id)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun save(drinkModel: DrinkModel): String? {
        return try {
            val snapshot =
                fireStore.collection("Drink").document().set(drinkModel.toRemote()).await()
            Log.d("SAVE SNAPSHOT", "$snapshot - ${snapshot.toString()}")
            null
        } catch (e: Exception) {
            e.message
        }
    }
}

fun RemoteDrink.toModel(id: String): DrinkModel {
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

fun DrinkModel.toRemote(): RemoteDrink {
    return RemoteDrink(
        name = name,
        description = description,
        origin = origin,
        photo = photo,
        timeRegister = timeRegister,
        timeUpdate = timeUpdate
    )
}