package com.lbe.imsdk.model.req

import com.google.gson.annotations.SerializedName

data class SessionIdBody(
    @SerializedName("sessionID")
    val sessionId: String,
)
