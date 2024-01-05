package com.hefengbao.jingmo.data.model.writing

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Quote(
    @SerialName("Book")
    val book: String,
    @SerialName("Section")
    val section: String?,
    @SerialName("Content")
    val content: String,
    @SerialName("IsComment")
    val isComment: Boolean = false,
    @SerialName("ReferrenceUrls")
    val referenceUrls: List<String>?
)
