package com.example.appca

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class productViewHolder (view: View):ViewHolder(view){
    val cbproducts:CheckBox=view.findViewById(R.id.cbProduct)
    val delete:ImageView=view.findViewById(R.id.delete)
    val update:ImageView=view.findViewById((R.id.update))
}
