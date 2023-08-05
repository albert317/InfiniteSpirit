package com.albert.commons.firebase.datasource

import com.albert.commons.firebase.remote.RemoteCategory
import com.albert.feature_home.data.datasource.CategoryRemoteDataSource
import com.albert.feature_home.domain.CategoryModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CategoryCloudFireStoreDataSource @Inject constructor(private val fireStore: FirebaseFirestore) :
    CategoryRemoteDataSource {
    override suspend fun categories(): List<CategoryModel> = try {
        val snapshot = fireStore.collection("Category").get().await()
        val categories = snapshot.documents.mapNotNull {
            it.toObject(RemoteCategory::class.java)?.toModel(it.id)
        }
        categories
    } catch (e: Exception) {
        emptyList()
    }

    override suspend fun findById(id: String): CategoryModel? = try {
        val snapshot = fireStore.collection("Category").document(id).get().await()
        snapshot.toObject(RemoteCategory::class.java)?.toModel(snapshot.id)
    } catch (e: Exception) {
        null
    }

    override suspend fun save(categoryModel: CategoryModel): String? = try {
        val newDocument = fireStore.collection("Category").document()
        newDocument.set(categoryModel.toRemote()).await()
        newDocument.id
    } catch (e: Exception) {
        null
    }

    private fun RemoteCategory.toModel(id: String) = CategoryModel(
        id = id,
        name = name ?: "",
        description = description ?: "",
        photo = photo ?: "",
        timeRegister = timeRegister ?: "",
        timeUpdate = timeUpdate ?: ""
    )

    private fun CategoryModel.toRemote() = RemoteCategory(
        name = name,
        description = description,
        photo = photo,
        timeRegister = timeRegister,
        timeUpdate = timeUpdate
    )
}