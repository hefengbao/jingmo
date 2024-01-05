package com.hefengbao.jingmo.data.model.writing

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Clause(
    @SerialName("Content")
    val content: String,
    @SerialName("Tones")
    val tones: List<Int>?,
    @SerialName("Comments")
    val comments: List<Comment>?,
    @SerialName("BreakAfter")
    val breakAfter: Int?
)
