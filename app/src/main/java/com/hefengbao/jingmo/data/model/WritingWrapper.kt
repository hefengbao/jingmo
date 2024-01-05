package com.hefengbao.jingmo.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WritingWrapper(
    val data: List<Writing>,
    @SerialName("next_page")
    val nextPage: String?
)
