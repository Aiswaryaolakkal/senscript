package com.example.moive.data.network

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiClient @Inject constructor()  {
    lateinit var  apiInterface: AlbumService
    val BASE_URL = "https://dummyjson.com/"

    private val SUFFIX = "api/Android/"

    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .apply {
            connectTimeout(20, TimeUnit.MINUTES)
            readTimeout(20, TimeUnit.MINUTES)
            writeTimeout(20, TimeUnit.MINUTES)
            interceptors().addAll(getInterceptors())
        }.build()

    init {
        makeService()
    }

    @Singleton
    private fun makeService() {
        val retrofit: Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterface = retrofit.create(AlbumService::class.java)
    }

    @Singleton
    private fun getInterceptors(): List<Interceptor> {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val authInterceptor: Interceptor = object : Interceptor {

            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                val original: Request = chain.request()
                val builder: Request.Builder = original.newBuilder()
                    .header("Content-Type", "application/json")

                val request: Request = builder.build()
                return chain.proceed(request)
            }

        }

        return mutableListOf<Interceptor>().apply {
            add(loggingInterceptor)
            add(authInterceptor)
        }.toList()

    }

}