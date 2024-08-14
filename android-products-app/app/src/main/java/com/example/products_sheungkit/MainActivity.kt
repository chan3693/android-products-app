package com.example.products_sheungkit

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.products_sheungkit.adapters.ProductAdapter
import com.example.products_sheungkit.databinding.ActivityMainBinding
import com.example.products_sheungkit.models.Product
import com.example.products_sheungkit.models.ProductResponse
import com.example.session01_starter.retrofit.JSONPlaceholderInterface
import com.example.session01_starter.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var api: JSONPlaceholderInterface
    lateinit var category: String
    lateinit var adapter: ProductAdapter
    var productList:MutableList<Product> = mutableListOf()
    val onClick = {
        rowNumber:Int ->
        val intent = Intent(this@MainActivity, Screen2Activity::class.java)
        intent.putExtra("PRODUCT_DETAILS", productList[rowNumber])
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.myToolbar)

        api = RetrofitInstance.retrofitService

        adapter = ProductAdapter(productList, onClick)

        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        getProduct("smartphones") // default screen
        binding.allProduct.text = "Smartphones"
    }

    private fun getProduct(category: String){
        lifecycleScope.launch {
            val responseFromAPI:ProductResponse = api.getProduct(category)
            val products:List<Product> =responseFromAPI.products
            productList.clear()
            productList.addAll(products)
            adapter.notifyDataSetChanged()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_smartphones -> {
                category = "smartphones"
                getProduct(category)
                binding.allProduct.text = "Smartphones"
                true
            }

            R.id.mi_laptops -> {
                category = "laptops"
                getProduct(category)
                binding.allProduct.text = "Laptops"
                true
            }

            R.id.mi_skincare -> {
                category = "skincare"
                getProduct(category)
                binding.allProduct.text = "Skincare"
                true
            }

            R.id.mi_groceries -> {
                category = "groceries"
                getProduct(category)
                binding.allProduct.text = "Groceries"
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}