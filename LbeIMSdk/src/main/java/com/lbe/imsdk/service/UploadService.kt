package com.lbe.imsdk.service

import com.lbe.imsdk.model.req.CompleteMultiPartUploadReq
import com.lbe.imsdk.model.req.InitMultiPartUploadBody
import com.lbe.imsdk.model.resp.CompleteMultiPartUploadRep
import com.lbe.imsdk.model.resp.InitMultiPartUploadRep
import com.lbe.imsdk.model.resp.SingleUploadRep
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Url

private const val TOKEN = "lbeToken"

const val SINGLE_UPLOAD = "api/single/fileupload"
const val INIT_MULTI_PART_UPLOAD = "api/multi/initiate-multipart_upload"
const val COMPLETE_MULTI_PART_UPLOAD = "api/multi/complete-multipart-upload"

interface UploadService {

    @GET
    suspend fun downloadImage(
        @Url url: String
    ): ResponseBody

    @POST(SINGLE_UPLOAD)
    @Multipart
    suspend fun singleUpload(
        @Header(TOKEN) token: String,
        @Part file: MultipartBody.Part,
        @Part("sign_type") signType: Int,
    ): SingleUploadRep

    @POST(INIT_MULTI_PART_UPLOAD)
    suspend fun initMultiPartUpload(
        @Header(TOKEN) token: String,
        @Body body: InitMultiPartUploadBody
    ): InitMultiPartUploadRep

    @PUT
    suspend fun uploadBinary(
        @Url url: String,
        @Body requestBody: RequestBody
    )

    @POST(COMPLETE_MULTI_PART_UPLOAD)
    suspend fun completeMultiPartUpload(
        @Header(TOKEN) token: String,
        @Body body: CompleteMultiPartUploadReq
    ): CompleteMultiPartUploadRep
}