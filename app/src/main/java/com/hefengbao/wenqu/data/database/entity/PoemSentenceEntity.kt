package com.hefengbao.wenqu.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PoemSentence(
    val id: Long,
    val content: String,
    val from: String,
    @SerialName("poem_id")
    val poemId: Long?
)
