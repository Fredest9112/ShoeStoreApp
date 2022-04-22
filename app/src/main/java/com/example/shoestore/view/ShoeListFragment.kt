package com.example.shoestore.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentShoeListBinding
import com.example.shoestore.databinding.ShoeItemBinding
import com.example.shoestore.model.ShoeStoreViewModel

class ShoeListFragment : Fragment() {

    private var binding: FragmentShoeListBinding? = null
    private val shoeStoreViewModel: ShoeStoreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentShoeListBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        setHasOptionsMenu(true)
        updateShoeList(inflater, container)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            shoeListFragment = this@ShoeListFragment
            lifecycleOwner = viewLifecycleOwner
        }
    }

    fun goToShoeDetail() {
        val action = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment()
        findNavController().navigate(action)
    }

    private fun updateShoeList(inflater: LayoutInflater, container: ViewGroup?) {
        val layout = binding?.shoeListLayout
        //here I based my code on a github repo uploaded by GerdanaT/shoeBox
        //however it is not exactly what she did
        //https://github.com/GerganaT/ShoeBox -> ShoeListFragment.kt
        shoeStoreViewModel.shoes.observe(viewLifecycleOwner) {
            it.forEach { shoe ->
                val shoeItemBinding: ShoeItemBinding =
                    DataBindingUtil.inflate(inflater, R.layout.shoe_item, container, false)
                shoeItemBinding.apply {
                    shoeName.text = shoe.name
                    shoeSize.text = shoe.size.toString()
                    shoeCompany.text = shoe.company
                    shoeDesc.text = shoe.description
                }
                layout?.addView(shoeItemBinding.root)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}