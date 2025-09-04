package com.lbe.imsdk.model

import com.google.gson.Gson
import com.lbe.imsdk.model.resp.IconUrl
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class MessageEntity : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()

    var sessionId: String = ""
    var msgBody: String = ""
    var senderUid: String = ""
    var msgType: Int = 0
    var msgSeq: Int = 0

    var clientMsgID: String = ""

    var sendTime: Long = 0L

    var faqListTile: String = ""

    // true: send success; false: send fail
    var sendSuccess: Boolean = true

    // true: read; false: no read yet
    var readed: Boolean = false

    var pendingUpload: Boolean = false

//    var canPending: Boolean = false

    var localFile: LocalMediaFile? = null

    var uploadTask: UploadTask? = UploadTask()

    var customerServiceNickname: String = ""
    var customerServiceAvatar: String = ""

    var timestamp: RealmInstant = RealmInstant.now()

    companion object {
        fun copy(source: MessageEntity): MessageEntity {
            val message = MessageEntity()
            message._id = source._id
            message.sessionId = source.sessionId
            message.msgType = source.msgType
            message.msgBody = source.msgBody
            message.senderUid = source.senderUid
            message.msgSeq = source.msgSeq
            message.clientMsgID = source.clientMsgID
            message.sendTime = source.sendTime
            message.sendSuccess = source.sendSuccess
            message.readed = source.readed
            message.pendingUpload = source.pendingUpload
            message.localFile = source.localFile
            message.uploadTask = source.uploadTask
//            message.canPending = source.canPending
            return message
        }
    }

    @Ignore
    val csAvatar: Any? by lazy {
        try {
            if (customerServiceAvatar.isEmpty()) {
                return@lazy customerServiceAvatar
            }
            return@lazy Gson().fromJson(customerServiceAvatar, IconUrl::class.java)
        } catch (e: Exception) {
            return@lazy customerServiceAvatar
        }
    }

    override fun toString(): String {
        return "MessageEntity(sessionId: $sessionId, senderUid: $senderUid, msgBody: $msgBody, msgType: $msgType, msgSeq: $msgSeq, clientMsgID: $clientMsgID, sendTime: $sendTime, sendSuccess: $sendSuccess, readed: $readed, pendingUpload: $pendingUpload, \n uploadTask: $uploadTask, \n localFile: $localFile)"
    }
}

class LocalMediaFile : EmbeddedRealmObject {
    var fileName: String = ""
    var path: String = ""
    var size: Long = 0
    var isBigFile: Boolean = false
    var mimeType: String = ""
    var width: Int = 0
    var height: Int = 0

    override fun toString(): String {
        return "LocalMediaFile(fileName: $fileName, path: $path, size: $size, isBigFile: $isBigFile, mimeType: $mimeType, width: $width, height: $height)"
    }
}

enum class UploadStatus {
    INIT,
    THUMBNAIL_UPLOADED,
    CHUNKS_INIT,
    UPLOADING,
    CHUNKS_MERGE_COMPLETED
}

class UploadTask : EmbeddedRealmObject {
    var progress: Float = 0.0f
    var taskLength: Int = 0
    var executeIndex: Int = 0
    var initTrunksRepJson: String = ""
    var reqBodyJson: String = ""
    var lastTrunkUploadLength: Long = 0
    var uploadStatus: String = UploadStatus.INIT.name

    override fun toString(): String {
        return "UploadTask(progress:$progress, taskLength: $taskLength, uploadStatus: $uploadStatus ,executeIndex: $executeIndex,  lastTrunkUploadLength: $lastTrunkUploadLength,\n initTrunksRepJson --->>> $initTrunksRepJson,\n reqBodyJson --->>> $reqBodyJson)"
    }
}