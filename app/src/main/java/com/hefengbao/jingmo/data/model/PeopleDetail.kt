package com.hefengbao.jingmo.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 人物资料
 */
@Serializable
data class PeopleDetail(
    @SerialName("Book")
    val book: String,
    @SerialName("Section")
    val section: String?,
    @SerialName("Content")
    val content: String?,
    @SerialName("IsReview")
    val isReview: Boolean,
    @SerialName("ReferrenceUrls")
    val referenceUrls: List<String>?
)
