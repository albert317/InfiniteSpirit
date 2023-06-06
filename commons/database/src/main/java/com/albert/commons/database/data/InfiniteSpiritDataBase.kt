package com.albert.commons.database.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.albert.commons.database.data.dao.CategoryDao
import com.albert.commons.database.data.dao.DrinkDao
import com.albert.commons.database.data.entity.CategoryEntity
import com.albert.commons.database.data.entity.DrinkEntity

@Database(entities = [DrinkEntity::class, CategoryEntity::class], version = 1)
abstract class InfiniteSpiritDataBase : RoomDatabase() {
    abstract fun drinkDao(): DrinkDao
    abstract fun categoryDao(): CategoryDao
}