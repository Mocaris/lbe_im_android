package com.lbe.imsdk.model.req

import androidx.annotation.IntDef

/**
 *
 * @Author mocaris
 * @Date 2026-01-19
 * @Since
 */
@Retention(AnnotationRetention.SOURCE)
@IntDef(
    value = [
        CustomMessageType.TYPE_TIME_OUT_REPLY
    ]
)
annotation class CustomMessageType {
    companion object {
        // 超时未回复
        const val TYPE_TIME_OUT_REPLY = -100
    }

}