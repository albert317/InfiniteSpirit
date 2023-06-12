package com.albert.commons.database.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.albert.commons.database.data.dao.CategoryDao
import com.albert.commons.database.data.dao.DrinkDao
import com.albert.commons.database.data.dao.DrinkIngredientDao
import com.albert.commons.database.data.dao.IngredientDao
import com.albert.commons.database.data.dao.ManagerDao
import com.albert.commons.database.data.entity.CategoryEntity
import com.albert.commons.database.data.entity.DrinkEntity
import com.albert.commons.database.data.entity.DrinkIngredientEntity
import com.albert.commons.database.data.entity.IngredientEntity
import com.albert.commons.database.data.entity.ManagerEntity

@Database(
    entities = [DrinkEntity::class, CategoryEntity::class, IngredientEntity::class, DrinkIngredientEntity::class, ManagerEntity::class],
    version = 1
)
abstract class InfiniteSpiritDataBase : RoomDatabase() {
    abstract fun drinkDao(): DrinkDao
    abstract fun categoryDao(): CategoryDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun managerDao(): ManagerDao
    abstract fun drinkIngredientDao(): DrinkIngredientDao

}