package com.hefengbao.jingmo.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PeopleWrapper(
    val data: List<People>,
    @SerialName("next_page")
    val nextPage: String?
)
