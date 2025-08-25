package com.hefengbao.jingmo.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataWrapper<T>(
    val total: Int,
    val data: List<T>,
    @SerialName("next_page")
    val nextPage: Int?
)
