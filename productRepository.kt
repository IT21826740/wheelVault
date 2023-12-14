package com.example.appca.database


class productRepository (
    private val db:productDatabase
){
    suspend fun insert(products: Products)=db.getproductDAO().insert(products)
    suspend fun delete(products: Products)=db.getproductDAO().delete(products)
    suspend fun update(products: Products)=db.getproductDAO().update(products)

    fun getAllProducts():List<Products> = db.getproductDAO().getAllProducts()
}
