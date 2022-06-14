package com.example.ecommerce

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


private lateinit var fAuth : FirebaseAuth
/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var etUserName: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var progressBar : ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_register, container, false)
        etUserName = view.findViewById(R.id.regUsername)
        etPassword = view.findViewById(R.id.regPassword)
        etConfirmPassword = view.findViewById(R.id.regConfirmPassword)
        progressBar = view.findViewById(R.id.progressBar)
        fAuth = FirebaseAuth.getInstance()




        view.findViewById<Button>(R.id.btnLoginReg).setOnClickListener {
            var navRegister = activity as FragmentNavigation
            navRegister.navigateFrag(LoginFragment(), false)
        }
        view.findViewById<Button>(R.id.btnRegisterReg).setOnClickListener {
            validateFormData()
        }

        return view;
    }

    fun firebaseSignUp(){
        progressBar.visibility = View.VISIBLE
        fAuth.createUserWithEmailAndPassword(etUserName.text.toString(),etPassword.text.toString()).addOnCompleteListener{
            task ->
            if(task.isSuccessful) {
                var navRegister = activity as FragmentNavigation
                navRegister.navigateFrag(HomeFragment(), true)
            }
            else {
                progressBar.visibility = View.GONE
                Toast.makeText(context, "registration failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun validateFormData() {
        when {
            TextUtils.isEmpty(etUserName.text.toString().trim()) -> {
                etUserName.setError("Please Enter Username")
            }
            TextUtils.isEmpty(etPassword.text.toString().trim()) -> {
                etPassword.setError("Please Enter Password")
            }
            TextUtils.isEmpty(etConfirmPassword.text.toString().trim()) -> {
                etConfirmPassword.setError("Please Enter Password Again")
            }
            etUserName.text.toString().isNotEmpty() &&
                    etPassword.text.toString().isNotEmpty() &&
                    etConfirmPassword.text.toString().isNotEmpty() -> {

                if (!etUserName.text.toString().matches(Regex("[a-zA-Z0-9._]+@[a-z]+\\.+[a-z]+"))) {
                    etUserName.setError("Please Enter Valid Emai")
                }
                else if (!etPassword.text.toString().equals(etConfirmPassword.text.toString())) {
                    etConfirmPassword.setError("Password not matching")
                }
                else{
                    firebaseSignUp()
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}