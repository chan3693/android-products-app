package com.example.products_sheungkit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.products_sheungkit.R
import com.example.products_sheungkit.models.Product

class ProductAdapter(
    var yourListData:List<Product>,
    var functionOnClick: (Int)->Unit)
    : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_layout, parent, false)

        return ProductViewHolder(view)
    }


    override fun getItemCount(): Int {
        return yourListData.size
    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product:Product = yourListData.get(position)

        val tvProductTitle = holder.itemView.findViewById<TextView>(R.id.tvProductTitle)
        tvProductTitle.text = product.title

        val tvProductPrice = holder.itemView.findViewById<TextView>(R.id.tvProductPrice)
        tvProductPrice.text = "$ ${product.price}"

        val onClick = holder.itemView.findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.btn_to_product_details)
        onClick.setOnClickListener{
            functionOnClick(position)
        }
    }
}