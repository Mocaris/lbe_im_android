package com.lbe.imsdk.model.resp

data class AnswerTimeoutContent(
// 超时时间 秒
    val timeout: Long,
) {
    fun getTimeoutMinutes(): Long {
        return timeout / 60
    }
}