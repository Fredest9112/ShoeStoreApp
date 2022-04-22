package com.example.shoestore.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentLoginBinding
import com.example.shoestore.model.ShoeStoreViewModel

class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null
    private val shoeStoreViewModel: ShoeStoreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentLoginBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            loginFragment = this@LoginFragment
            lifecycleOwner = viewLifecycleOwner
            viewModel = shoeStoreViewModel
        }
    }

    fun goToWelcomeFragment() {
        binding?.apply {
            shoeStoreViewModel.apply {
                if (nameInput.text.isNotEmpty() && passInput.text.isNotEmpty()) {
                    setUserName(nameInput.text.toString())
                    setUserPass(passInput.text.toString())
                    isUserLogged()
                    val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
                    findNavController().navigate(action)
                } else {
                    Toast.makeText(
                        activity,
                        resources.getString(R.string.error_no_credentials),
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}