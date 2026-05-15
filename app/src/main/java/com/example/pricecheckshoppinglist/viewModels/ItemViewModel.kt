package com.example.pricecheckshoppinglist.viewModels

import androidx.lifecycle.ViewModel
import com.example.pricecheckshoppinglist.models.Item
import com.example.pricecheckshoppinglist.models.ItemPrice
import com.example.pricecheckshoppinglist.models.Store
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ItemViewModel : ViewModel() {
    private val _allItems = MutableStateFlow<List<Item>>(emptyList())
    val allItems: StateFlow<List<Item>> = _allItems

    fun addItem(itemName: String){
        _allItems.value += Item(itemName)
    }
/**Edit Later
    fun deleteStore(item: Item, store: Store){
        item.prices.value.forEach {
            if (it.store.name == store.name)
               // item.prices.removeAt(item.prices.indexOf(it))
        }
    }*/

    fun deleteItem(item: Item){
        val tempList = _allItems.value.toMutableList()
        tempList.forEach {
            if(it.name == item.name)
                tempList.removeAt(tempList.indexOf(it))
        }
        _allItems.value = tempList
    }

    fun editName(item: String, name: String){
        _allItems.value.forEach {
            if(it.name == item)
                it.name = name
        }
    }

    fun setStore(item: String, store: String) : Boolean{
        _allItems.value.forEach {
            if(it.name == item){
                it.prices.value.forEach {
                    if(it.store == store){
                        return false
                    }
                }
                it.prices.value += ItemPrice(store)
                return true
            }
        }
        return false
    }

    fun editPrice(item: String, store: String, price: Double){
        _allItems.value.forEach {
            if(it.name == item){
                it.prices.value.forEach {
                    if(it.store == store)
                        it.price = price
                }
            }
        }
    }

    fun editUnit(item: String, store: String, unit: String){
        _allItems.value.forEach {
            if(it.name == item){
                it.prices.value.forEach {
                    if(it.store == store)
                        it.unit = unit
                }
            }
        }
    }

    fun editQuantity(item: String, store: String, quantity: Double){
        _allItems.value.forEach {
            if(it.name == item){
                it.prices.value.forEach {
                    if(it.store == store)
                        it.quantity = quantity
                }
            }
        }
    }

    fun getPrice(item: String, store: String) : Double{
        _allItems.value.forEach {
            if(it.name == item){
                it.prices.value.forEach {
                    if(it.store == store)
                        return it.price
                }
            }
        }
        return 0.0
    }

    fun getUnit(item: String, store: String) : String{
        _allItems.value.forEach {
            if(it.name == item){
                it.prices.value.forEach {
                    if(it.store == store)
                        return it.unit
                }
            }
        }
        return ""
    }

    fun getQuantity(item: String, store: String) : Double{
        _allItems.value.forEach {
            if(it.name == item){
                it.prices.value.forEach {
                    if(it.store == store)
                        return it.quantity
                }
            }
        }
        return 0.0
    }

    fun itemCount() : Int {
        return _allItems.value.count()
    }
}