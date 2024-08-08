package com.android.imageresearcher.DataClass

import com.google.gson.annotations.SerializedName
import java.time.ZonedDateTime

data class KakaoResponse(
    @SerializedName("meta")
    val KakaoMeta: ResponseMeta,

    @SerializedName("documents")
    val KakaoDocuments: ResponseDocuments
)

data class ResponseMeta(
    val total_count: Int,
    val pageable_count: Int,
    val is_end:Boolean
)

data class ResponseDocuments(
    val DocumentsList: MutableList<Document>
)

data class Document(
    val collection: String,
    val thumbnail_url: String,
    val image_url: String,
    val width: Int,
    val height: Int,
    val display_sitename: String,
    val doc_url: String,
    val datetime: ZonedDateTime
)