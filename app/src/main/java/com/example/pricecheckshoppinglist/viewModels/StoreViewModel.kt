package com.example.pricecheckshoppinglist.viewModels

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import com.example.pricecheckshoppinglist.dataStore
import com.example.pricecheckshoppinglist.models.Store
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class StoreViewModel : ViewModel() {
    private val _stores = MutableStateFlow<List<Store>>(emptyList())
    val stores: StateFlow<List<Store>> = _stores
    var storeKey = "store_"
    fun key() = stringPreferencesKey(storeKey)

    suspend fun save(context: Context, store: String){
        _stores.value.forEach {
            if(it.name == store) {
                storeKey += it.id
                val json = Json.encodeToString(it)
                context.dataStore.edit { it[key()] = json }
            }
        }
    }

    suspend fun load(context: Context, keyInt: Int){
        storeKey += keyInt
        val json: String? = context.dataStore.data.map { it[key()] }.first()
        if(json != null){
            _stores.value += Json.decodeFromString<Store>(json)
        }
    }

    fun addStore(storeName: String) : Boolean {
        var duplicate = false

      //  _stores.value.forEach {
        //    while (!duplicate)
          //      if(it.name == storeName)
            //        duplicate = true
        //}
        //if(!duplicate) {
        _stores.value += Store(_stores.value.size, storeName)
        //}

        return (!duplicate)
    }

    /**
    fun deleteStore(storeName: String) {
        val tempList = _stores.value.toMutableList()

        tempList.forEach {
            if (it.name == storeName)
                tempList.removeAt(tempList.indexOf(it))
        }

        _stores.value = tempList
    }*/

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

    fun getCity(name: String) : String{
        _stores.value.forEach {
            if(it.name == name)
                return it.city
        }
        return ""
    }

    fun getTax(name: String): Double {
        _stores.value.forEach {
            if(it.name == name)
                return it.tax
        }
        return 0.0
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