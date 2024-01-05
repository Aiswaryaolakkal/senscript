package com.example.moive.data.repo

import com.example.moive.data.network.ApiClient
import javax.inject.Inject

class ProductRepo @Inject constructor( private val client: ApiClient){
    suspend fun getSmartPhoneList()= client.apiInterface.getSmartPhone()
    suspend fun getMoive()= client.apiInterface.getMovie(" eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0MzgyZmY2MGIyNDVmMGFhMWY4ZGZkODE4Y2E1ODYzMiIsInN1YiI6IjY1NzE1NGMzN2ViNWYyMDBjOTQzZjhiOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Ghkl-MQQWZxuf3JUCD4elhojEiR8XEzjKR7SGSH_t4g")

    suspend fun getProductList()=  client.apiInterface.getProductList()

}