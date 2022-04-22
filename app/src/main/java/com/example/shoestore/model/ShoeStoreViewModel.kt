package com.example.shoestore.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoestore.data.Shoe

class ShoeStoreViewModel : ViewModel() {

    private val _userName = MutableLiveData<String>()

    private val _userPass = MutableLiveData<String>()

    private val _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes: LiveData<MutableList<Shoe>> = _shoes

    init {
        _shoes.value = mutableListOf()
    }

    fun setUserName(userName: String) {
        _userName.value = userName
    }

    fun setUserPass(userPass: String) {
        _userPass.value = userPass
    }

    fun setShoesList(shoe: Shoe) {
        _shoes.value?.add(shoe)
        _shoes.value = _shoes.value
    }

    fun isUserLogged(): Boolean {
        return _userPass.value?.isNotEmpty() == true && _userName.value?.isNotEmpty() == true
    }
}