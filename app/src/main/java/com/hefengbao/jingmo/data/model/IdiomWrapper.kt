package com.hefengbao.jingmo.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IdiomWrapper(
    val data: List<Idiom>,
    @SerialName("next_page")
    val nextPage: String?
)
