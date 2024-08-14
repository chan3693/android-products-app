package com.example.products_sheungkit

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.products_sheungkit.databinding.ActivityMainBinding
import com.example.products_sheungkit.databinding.ActivityScreen2Binding
import com.example.products_sheungkit.models.Product
import com.example.session01_starter.retrofit.JSONPlaceholderInterface
import com.example.session01_starter.retrofit.RetrofitInstance
import kotlinx.coroutines.launch



class Screen2Activity : AppCompatActivity() {
    lateinit var binding: ActivityScreen2Binding
    lateinit var api: JSONPlaceholderInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreen2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        api = RetrofitInstance.retrofitService

        val product: Product = intent.getSerializableExtra("PRODUCT_DETAILS") as Product
        val id: Int = product.id

        lifecycleScope.launch {
            val item: Product = api.getProductById(id)

            val output = """
                Title: ${item.title}
                Description: ${item.description}
                Price: $${item.price}
                Brand: ${item.brand}
                """.trimIndent()
            binding.itemDetails.setText(output)

            Glide.with(this@Screen2Activity)
                .load(item.thumbnail)
                .into(binding.thumbnailImage)
        }
    }
}
