package com.example.appca

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appca.database.Products

class MainActivityData: ViewModel() {
        private val _data= MutableLiveData<List<Products>>()

        val data:LiveData<List<Products>> = _data

        fun setData(data:List<Products>) {
            _data.value=data
        }
    }
