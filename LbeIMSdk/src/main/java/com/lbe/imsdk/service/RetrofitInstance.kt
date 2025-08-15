package com.lbe.imsdk.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://mob.imsz.online/"

/**
 * [baseUrl] load api url base url
 * [imBaseUrl] im api base url
 * [fileBaseUrl] file upload base url
 */
//class RetrofitInstance(
//    private val baseUrl: String = BASE_URL,
//    private val imBaseUrl: String,
//    private val fileBaseUrl: String,
//) {
//
//    // uat
//
//    // sit
////    private const val BASE_URL = "http://www.im-sit-dreaminglife.cn/"
//
//    // dev
////     private const val BASE_URL = "http://www.im-dreaminglife.cn/"
//
////    var IM_URL = ""
////    var UPLOAD_BASE_URL = ""
//
//
//
//
//}