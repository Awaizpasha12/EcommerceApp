package com.example.ecommerce

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce.Adapter.ProductAdapter
import com.google.firebase.database.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var productList = ArrayList<ProductNew>()
    private var database : FirebaseDatabase? = null
    private var reference : DatabaseReference ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_home, container, false)
        val rv_mainView = view.findViewById<RecyclerView>(R.id.rv_mainView)
        rv_mainView.setHasFixedSize(true)
        rv_mainView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        database = FirebaseDatabase.getInstance()
        reference = database!!.getReference("products")
        val FirebaseListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                val child = snapshot.children
                child.forEach {
                    val map = it.child("img").value as HashMap<String,String>
                    val list: ArrayList<String> = ArrayList<String>(map.values)

                    var product = ProductNew(
                        list,
                        it.child("name").value.toString(),
                        it.child("price").value.toString()
                    )
                    productList.add(product)
                }
                var mainActivityView = (activity as MainActivity)
                val adapter = ProductAdapter(productList, mainActivityView)
                rv_mainView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("snapshot", "DatabaseError.toString()")
            }

        }
        reference?.addValueEventListener(FirebaseListener)




//        view.findViewById<Button>(R.id.btnLogout).setOnClickListener{
//            FirebaseAuth.getInstance().signOut();
            var navRegister = activity as FragmentNavigation
//            navRegister.navigateFrag(LoginFragment(), true)
//        }
        return view;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}