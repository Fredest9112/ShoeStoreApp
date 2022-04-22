package com.example.shoestore.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shoestore.data.Shoe
import com.example.shoestore.databinding.FragmentShoeDetailBinding
import com.example.shoestore.model.ShoeStoreViewModel

class ShoeDetailFragment : Fragment() {

    private var binding: FragmentShoeDetailBinding? = null
    private val shoeStoreViewModel: ShoeStoreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentShoeDetailBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            shoeDetailFragment = this@ShoeDetailFragment
        }
    }

    fun onCancelShoePressed() {
        binding?.apply {
            shoeNameInput.text.clear()
            shoeCompanyInput.text.clear()
            shoeDescInput.text.clear()
            shoeImageInput.text.clear()
            shoeSizeInput.text.clear()
        }
        val action = ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment()
        findNavController().navigate(action)
    }

    fun onSaveShoePressed() {
        binding?.apply {
            val newShoe = Shoe(
                shoeNameInput.text.toString(),
                shoeSizeInput.text.toString().toDouble(),
                shoeCompanyInput.text.toString(),
                shoeDescInput.text.toString()
            )
            shoeStoreViewModel.setShoesList(newShoe)
        }
        val action = ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment()
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}