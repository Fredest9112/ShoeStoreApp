package com.example.shoestore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shoestore.data.ValidateInputs.validateLoginCredentials
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
                if (validateLoginCredentials(
                        nameInput.text.toString(),
                        passInput.text.toString(),
                        resources,
                        activity
                    )
                ) {
                    setUserName(nameInput.text.toString())
                    setUserPass(passInput.text.toString())
                    isUserLogged()
                    val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
                    findNavController().navigate(action)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}