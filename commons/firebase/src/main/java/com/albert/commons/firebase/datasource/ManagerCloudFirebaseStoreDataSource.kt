package com.albert.commons.firebase.datasource

import com.albert.commons.firebase.remote.RemoteManager
import com.albert.feature_home.data.datasource.ManagerRemoteDataSource
import com.albert.feature_home.domain.ManagerModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ManagerCloudFirebaseStoreDataSource @Inject constructor(private val fireStore: FirebaseFirestore) :
    ManagerRemoteDataSource {

    override suspend fun managers(): List<ManagerModel> = try {
        val snapshot = fireStore.collection("ManagerDD").get().await()
        val managers = snapshot.documents.mapNotNull {
            it.toObject(RemoteManager::class.java)?.toModel(it.id)
        }
        managers
    } catch (e: Exception) {
        emptyList()
    }

    override suspend fun findById(id: String): ManagerModel? = try {
        val snapshot = fireStore.collection("ManagerDD").document(id).get().await()
        snapshot.toObject(RemoteManager::class.java)?.toModel(snapshot.id)
    } catch (e: Exception) {
        null
    }

    override suspend fun save(managerModel: ManagerModel): String? = try {
        fireStore.collection("ManagerDD").document().set(managerModel.toRemote()).await()
        null
    } catch (e: Exception) {
        e.message
    }

    private fun RemoteManager.toModel(id: String) = ManagerModel(
        id = id,
        name = name.toString(),
        isUpdateRequired = true,
        timeRegister = timeRegister.toString(),
        timeUpdate = timeUpdate.toString()
    )

    private fun ManagerModel.toRemote() = RemoteManager(
        name = name, timeRegister = timeRegister, timeUpdate = timeUpdate
    )
}