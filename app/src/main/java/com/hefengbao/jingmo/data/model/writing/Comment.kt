package com.hefengbao.jingmo.data.model.writing

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    @SerialName("Category")
    val category: String?,
    @SerialName("Content")
    val content: String,
    @SerialName("Type")
    val type: String,
    @SerialName("Index")
    val index: Int
)
