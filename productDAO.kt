package com.example.appca.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface productDAO {
    @Insert
    suspend fun insert(products: Products)

    @Delete
    suspend fun delete(products: Products)

    @Update
    suspend fun update(products: Products)

    @Query("select*from products")
    fun getAllProducts():List<Products>
