package com.example.ecommerce

import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import java.lang.Exception


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductDetails.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductDetails(product: ProductNew,transitionName : String) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var tvProductTitle : TextView? = null
    var tvProductDescription : TextView? = null
    var tvProductPrice : TextView? = null

    var product1 : ProductNew = product
    var transitionName1 : String = transitionName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        postponeEnterTransition()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(R.transition.change_image_transform)
        }
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_product_details, container, false)
        tvProductTitle = view.findViewById(R.id.tvProductTitle)
        tvProductDescription = view.findViewById(R.id.tvProductDescription)
        tvProductPrice = view.findViewById(R.id.tvProductPrice)

        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var imageListener: ImageListener = object : ImageListener {
            override fun setImageForPosition(position: Int, imageView: ImageView) {
                // You can use Glide or Picasso here
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imageView.transitionName = transitionName1
                }
                Picasso.get()
                    .load(product1.img[position])
                    .noFade()
                    .into(imageView, object : Callback {
                        override fun onSuccess() {
                            startPostponedEnterTransition()
                        }

                        override fun onError(e: Exception?) {
                            startPostponedEnterTransition()
                        }


                    })
            }
        }
        val carouselView = view.findViewById(R.id.carouselView) as CarouselView;
        carouselView.setImageListener(imageListener);
        carouselView.setPageCount(product1.img.size);
        tvProductTitle?.text = product1.title
        tvProductPrice?.text = product1.price
    }
}