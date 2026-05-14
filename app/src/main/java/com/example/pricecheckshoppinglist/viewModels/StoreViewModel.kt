package com.example.pricecheckshoppinglist.viewModels

import androidx.lifecycle.ViewModel
import com.example.pricecheckshoppinglist.models.Store
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StoreViewModel : ViewModel() {
    private val _stores = MutableStateFlow<List<Store>>(emptyList())
    val stores: StateFlow<List<Store>> = _stores

    fun addStore(storeName: String) : Boolean {
        var duplicate = false

      //  _stores.value.forEach {
        //    while (!duplicate)
          //      if(it.name == storeName)
            //        duplicate = true
        //}
        //if(!duplicate) {
        _stores.value += Store(storeName)
        //}

        return (!duplicate)
    }

    fun deleteStore(storeName: String) {
        val tempList = _stores.value.toMutableList()

        tempList.forEach {
            if (it.name == storeName)
                tempList.removeAt(tempList.indexOf(it))
        }

        _stores.value = tempList
    }

    fun editName(originalName: String, newName: String) : String {
        var duplicate = false
        var name = originalName

        _stores.value.forEach {
            if(it.name == newName)
                duplicate = true
        }

        if(!duplicate)
            _stores.value.forEach {
                if (it.name == originalName) {
                    it.name = newName
                    name = newName
                }
            }

        return name
    }

    fun editCity(name: String, city: String) {
        _stores.value.forEach {
            if (it.name == name) {
                it.city = city
            }
        }
    }

    fun editTax(name: String, tax: Double) {
        _stores.value.forEach {
            if (it.name == name) {
                it.tax = tax
            }
        }
    }

    fun storeCount() : Int {
        return _stores.value.count()
    }
}