package com.hefengbao.jingmo.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 引用典故数据
 */
@Serializable
data class WritingAllusion(
    @SerialName("AllusionIndex")
    val allusionIndex: Int,
    @SerialName("AllusionKey")
    val allusionKey: String,
    @SerialName("SentenceIndex")
    val sentenceIndex: Int,
)
