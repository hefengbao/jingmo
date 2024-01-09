package com.hefengbao.jingmo.data.model.writing

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tune(
    @SerialName("Name")
    val name: String? = null,
    @SerialName("Id")
    val id: Int?,
)
