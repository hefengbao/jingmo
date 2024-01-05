package com.hefengbao.jingmo.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 人物家乡
 */
@Serializable
data class PeopleHometown(
    @SerialName("RegionId")
    val regionId: String,
    @SerialName("Name")
    val name: String
)
