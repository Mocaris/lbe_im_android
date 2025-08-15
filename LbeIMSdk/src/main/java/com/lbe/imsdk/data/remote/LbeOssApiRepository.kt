package com.lbe.imsdk.data.remote

import com.lbe.imsdk.model.req.*
import com.lbe.imsdk.model.resp.*
import com.lbe.imsdk.service.*
import okhttp3.*
import retrofit2.*
import retrofit2.converter.gson.*

class LbeOssApiRepository(private val fileBaseUrl: String) {
//    private val uploadService = RetrofitInstance.uploadService

    val apiService: UploadService by lazy {
        val retrofit = Retrofit.Builder().baseUrl(fileBaseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
        retrofit.create(UploadService::class.java)
    }

    suspend fun singleUpload(
        file: MultipartBody.Part,
        signType: Int = 1,
        token: String,
    ): SingleUploadRep {
        return apiService.singleUpload(file = file, signType = signType, token = token)
    }

    suspend fun initMultiPartUpload(
        body: InitMultiPartUploadBody,
        token: String,
    ): InitMultiPartUploadRep {
        return apiService.initMultiPartUpload(body = body, token = token)
    }

    suspend fun uploadBinary(url: String, body: RequestBody) {
        apiService.uploadBinary(url = url, requestBody = body)
    }

    suspend fun completeMultiPartUpload(
        body: CompleteMultiPartUploadReq,
        token: String,
    ): CompleteMultiPartUploadRep {
        return apiService.completeMultiPartUpload(body = body, token = token)
    }
}