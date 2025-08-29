package com.lbe.imsdk.data.remote

import com.lbe.imsdk.model.req.*
import com.lbe.imsdk.model.resp.*
import com.lbe.imsdk.service.*
import okhttp3.*
import okhttp3.logging.*
import retrofit2.*
import retrofit2.converter.gson.*

class LbeImApiRepository(private val imBaseUrl:String) {
//    private val lbeIMRepository = RetrofitInstance.imApiService

    val imApiService: LbeIMAPiService by lazy {
        val retrofit = Retrofit.Builder().baseUrl(imBaseUrl).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
        retrofit.create(LbeIMAPiService::class.java)
    }

    suspend fun fetchSessionList(
        lbeToken: String, lbeIdentity: String, body: SessionListReq
    ): SessionListRep {
        return imApiService.fetchSessionList(
            lbeToken = lbeToken, lbeIdentity = lbeIdentity, body
        )
    }

    suspend fun createSession(lbeSign: String, lbeIdentity: String, body: SessionBody): Session {
        return imApiService.createSession(lbeSign = lbeSign, lbeIdentity = lbeIdentity, body)
    }

    suspend fun fetchHistory(
        lbeSign: String, lbeToken: String, lbeIdentity: String, body: HistoryBody
    ): History {
        return imApiService.fetchHistory(
            lbeSign = lbeSign, lbeToken = lbeToken, lbeIdentity = lbeIdentity, body = body
        )
    }

    suspend fun sendMsg(
        lbeToken: String, lbeSession: String, lbeIdentity: String, body: MsgBody
    ): SendMsg {
        return imApiService.sendMsg(
            lbeToken = lbeToken, lbeSession = lbeSession, lbeIdentity = lbeIdentity, body = body
        )
    }

    suspend fun fetchTimeoutConfig(
        lbeSign: String, lbeToken: String, lbeIdentity: String
    ): TimeoutRespBody {
        return imApiService.fetchTimeoutConfig(
            lbeSign = lbeSign,
            lbeToken = lbeToken,
            lbeIdentity = lbeIdentity,
            body = TimeoutReqBody(userType = 2)
        )
    }

    suspend fun markRead(
        lbeSign: String, lbeToken: String, lbeIdentity: String, body: MarkReadReqBody
    ) {
        imApiService.markRead(
            lbeSign = lbeSign, lbeToken = lbeToken, lbeIdentity = lbeIdentity, body = body
        )
    }

    suspend fun faq(
        lbeSession: String, lbeToken: String, lbeIdentity: String, body: FaqReqBody
    ): FaqResp {
        return imApiService.faq(
            lbeToken = lbeToken, lbeIdentity = lbeIdentity, lbeSession = lbeSession, body = body
        )
    }

    suspend fun turnCustomerService(
        lbeSign: String,
        lbeToken: String,
        lbeIdentity: String,
        lbeSession: String,
    ) {
        imApiService.turnCustomerService(
            lbeSign = lbeSign,
            lbeToken = lbeToken,
            lbeIdentity = lbeIdentity,
            lbeSession = lbeSession
        )
    }
}