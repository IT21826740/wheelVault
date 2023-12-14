package com.example.appca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appca.database.Products
import com.example.appca.database.productDatabase
import com.example.appca.database.productRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:MainActivityData
    private lateinit var adapter:productAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentContainer=findViewById<FrameLayout>(R.id.fragment_container)
        val btnMenu = findViewById<Button>(R.id.MenuBtn)

        btnMenu.setOnClickListener {
            val menuFragment= MenuFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, menuFragment)
                .addToBackStack(null)
                .commit()

                }

        val profileButton:Button =findViewById(R.id.ProfileBtn)
        profileButton.setOnClickListener {
            val intent =Intent(this@MainActivity,Login::class.java)
            startActivity(intent)
        }

        val recyclerView: RecyclerView = findViewById(R.id.rvProduct)
        val repository = productRepository(productDatabase.getInstence(this))

        viewModel = ViewModelProvider(this)[MainActivityData::class.java]

        viewModel.data.observe(this) {

            adapter = productAdapter(it, repository, viewModel)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)
        }

        CoroutineScope(Dispatchers.IO).launch {
            val data = repository. getAllProducts()

            runOnUiThread {
                viewModel.setData(data)
            }
        }

        val addItem: Button = findViewById(R.id.AddBtn)

        addItem.setOnClickListener {
            displayAlert(repository)
        }

    }

    private fun displayAlert(repository: productRepository) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("WheelVault")
        builder.setMessage("Enter Item Details Below")


        val nameInput = EditText(this)
        nameInput.hint = "Name "
        val descriptionInput = EditText(this)
        descriptionInput.hint = "Description"
        val priceInput = EditText(this)
        priceInput.hint = "Price"


        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.addView(nameInput)
        layout.addView(descriptionInput)
        layout.addView(priceInput)

        builder.setView(layout)

        builder.setPositiveButton("OK") { dialog, which ->
            val name = nameInput.text.toString()
            val description = priceInput.text.toString()
            val price = descriptionInput.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                repository.insert(Products(name, price, description ))

                val data = repository.getAllProducts()
                runOnUiThread {
                    viewModel.setData(data)
                }
            }
        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }

        val alertDialog = builder.create()
        alertDialog.show()



    }
}
