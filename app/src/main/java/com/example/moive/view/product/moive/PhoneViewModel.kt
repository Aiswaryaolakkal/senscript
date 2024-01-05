package com.example.moive.view.product.moive

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moive.data.repo.ProductRepo
import com.example.moive.view.model.DataItem
import com.example.moive.view.model.MovieResult
import com.example.moive.view.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhoneViewModel @Inject constructor(private val productRepo:ProductRepo) : ViewModel() {
val productList = MutableLiveData<List<Product>>()
val moiveList = MutableLiveData<List<MovieResult>>()
private val productItemList = MutableLiveData<List<DataItem>>()

    fun fetchAlbumList() {
        viewModelScope.launch {
            val apiRespo = productRepo.getSmartPhoneList()
            if (apiRespo.isSuccessful) {
                if(apiRespo.body()!=null) {
                    productList.value = apiRespo.body()!!.products
                }
//                productList.value?.forEach{
//                    productRepo.insertPhoneToDB(PhoneEntity( it.id,it.title,it.brand,it.price.toString()))
//                }

            }
        }

    }
    fun fetchMoiveList() {
        viewModelScope.launch {
            val apiRespo = productRepo.getMoive()
            if (apiRespo.isSuccessful) {
                if(apiRespo.body()!=null) {
                    moiveList.value = apiRespo.body()!!.results
                }
//                productList.value?.forEach{
//                    productRepo.insertPhoneToDB(PhoneEntity( it.id,it.title,it.brand,it.price.toString()))
//                }

            }
        }

    }
    fun fetchProductList() {
        viewModelScope.launch {
            val apiResp = productRepo.getProductList()
            if (apiResp.isSuccessful) {
                if(apiResp.body()!=null) {
                    productItemList.value = apiResp.body()!!.data
                }
              /*  productItemList.value?.forEach{
                    productRepo.insertPhoneToDB(PhoneEntity( it.id,it.title,it.brand,it.price.toString()))
                }*/

            }
        }

    }
}