package com.example.appca


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.appca.database.Products
import com.example.appca.database.productRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

    class productAdapter (
        private val items:List<Products>,
        private val repository: productRepository,
        private val viewModel: MainActivityData): RecyclerView.Adapter<productViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productViewHolder {
            val  context=parent.context
            val view = LayoutInflater.from(context).inflate(R.layout.viewproducts,parent,false)
            return productViewHolder(view)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: productViewHolder, position: Int) {

            val products=items[position]
            val productinfo =
                "${products.name},${products.description},${products.price}"
            holder.cbproducts.text= productinfo

            holder.delete.setOnClickListener {
                val isChecked = holder.cbproducts.isChecked

                if (isChecked) {
                    val context = holder.itemView.context

                    CoroutineScope(Dispatchers.IO).launch {
                        repository.delete(items.get(position))
                        val data = repository.getAllProducts()
                        withContext(Dispatchers.Main) {
                            viewModel.setData(data)
                        }
                    }
                } else {
                    Toast.makeText(holder.itemView.context, "Select the item to be deleted", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
