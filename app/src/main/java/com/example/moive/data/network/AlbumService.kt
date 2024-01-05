package com.example.moive.data.network

import com.example.moive.view.model.MovieModel
import com.example.moive.view.model.SmartPhone
import com.example.moive.view.model.wcItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header


interface AlbumService {



    @GET("movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc")
    suspend fun  getMovie( @Header("Authorization") header:String ): Response<MovieModel>

    @GET("products/category/smartphones")
    suspend fun  getSmartPhone(): Response<SmartPhone>

    @GET("v3/17db81c4-f48e-408a-bf06-c289ee825e06")
    suspend fun  getProductList(): Response<wcItem>
}