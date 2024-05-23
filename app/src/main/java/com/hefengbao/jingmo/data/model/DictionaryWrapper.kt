package com.hefengbao.jingmo.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DictionaryWrapper(
    val data: List<Dictionary>,
    @SerialName("next_page")
    val nextPage: String?
)
