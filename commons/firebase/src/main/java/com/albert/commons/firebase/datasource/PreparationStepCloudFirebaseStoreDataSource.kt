package com.albert.commons.firebase.datasource

import com.albert.commons.firebase.remote.RemotePreparationStep
import com.albert.feature_home.data.datasource.PreparationStepRemoteDataSource
import com.albert.feature_home.domain.PreparationStepModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PreparationStepCloudFirebaseStoreDataSource @Inject constructor(private val fireStore: FirebaseFirestore) :
    PreparationStepRemoteDataSource {

    override suspend fun preparationsStep(): List<PreparationStepModel> = try {
        val snapshot = fireStore.collection("PreparationStep").get().await()
        val preparations = snapshot.documents.mapNotNull {
            it.toObject(RemotePreparationStep::class.java)?.toModel(it.id)
        }
        preparations
    } catch (e: Exception) {
        emptyList()
    }

    override suspend fun findById(id: String): PreparationStepModel? {
        return try {
            val snapshot = fireStore.collection("PreparationStep").document(id).get().await()
            snapshot.toObject(RemotePreparationStep::class.java)?.toModel(snapshot.id)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun save(preparationStepModel: PreparationStepModel): String? {
        return try {
            val newDocument = fireStore.collection("PreparationStep").document()
            newDocument.set(preparationStepModel.toRemote()).await()
            newDocument.id
        } catch (e: Exception) {
            null
        }
    }

    private fun RemotePreparationStep.toModel(id: String) = PreparationStepModel(
        id = id,
        idDrink = idDrink ?: "",
        order = order ?: 0,
        description = description ?: "",
        timeRegister = timeRegister ?: "",
        timeUpdate = timeUpdate ?: "",
    )

    private fun PreparationStepModel.toRemote() = RemotePreparationStep(
        idDrink = idDrink,
        order = order,
        description = description,
        timeRegister = timeRegister,
        timeUpdate = timeUpdate,
    )
}