package com.lbe.imsdk.data.remote

import com.lbe.imsdk.model.req.*
import com.lbe.imsdk.model.resp.*
import com.lbe.imsdk.service.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LbeConfigApiRepository(private val baseUrl: String = BASE_URL) {

    val apiService: ApiService by lazy {
        val retrofit =
            Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .build()
        retrofit.create(ApiService::class.java)
    }

    suspend fun fetchConfig(lbeSign: String, lbeIdentity: String, body: ConfigBody): Config {
        return apiService.fetchConfig(
            lbeSign = lbeSign, lbeIdentity = lbeIdentity, body = body
        )
    }
}