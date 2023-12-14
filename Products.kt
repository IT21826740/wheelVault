package com.example.appca.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Products(
    val  name:String,
    val description:String,
    val price:String
)
{
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}

