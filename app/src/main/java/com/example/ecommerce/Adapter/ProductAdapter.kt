package com.example.ecommerce.Adapter

import android.app.Activity
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce.*
import com.squareup.picasso.Picasso


class ProductAdapter(productList: ArrayList<ProductNew>, activity: Activity) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    var productList = productList
    var activity = activity
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ProductViewHolder {
    val layoutView : View = LayoutInflater.from(parent.context).inflate(R.layout.product_card, parent, false)
        return ProductViewHolder(layoutView)

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) {

        Picasso.get().load(productList[position].img[0]).into(holder.ivProductImage)
        holder.tvProductTitle.text = productList[position].title
        holder.tvProductPrice.text = productList[position].price
        holder.cvProductCardView.setOnClickListener(View.OnClickListener {

            holder.ivProductImage.transitionName = productList[position].title
            var navRegister = activity as FragmentNavigation
            navRegister.navigateFragWithTransition(ProductDetails(productList[position],productList[position].title), true,"",holder.ivProductImage)
        })
    }

    override fun getItemCount(): Int {
        return productList.size
    }
    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view){

        var ivProductImage : ImageView = view.findViewById(R.id.iv_productImage)
        var tvProductTitle : TextView = view.findViewById(R.id.tv_productTitle)
        var tvProductPrice : TextView = view.findViewById(R.id.tv_productDescription)
        var llProductCardView : LinearLayout = view.findViewById(R.id.ll_ProductCardView)
        var cvProductCardView : CardView = view.findViewById(R.id.cv_productCardView)
    }
}