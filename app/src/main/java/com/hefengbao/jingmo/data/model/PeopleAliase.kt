package com.hefengbao.jingmo.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 人物别名
 */
@Serializable
data class PeopleAlias(
    @SerialName("Name")
    val name: String,
    @SerialName("Type")
    val type: String
)
