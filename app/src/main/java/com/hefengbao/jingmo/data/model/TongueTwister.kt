package com.hefengbao.jingmo.data.model

import com.hefengbao.jingmo.data.database.entity.TongueTwisterEntity
import kotlinx.serialization.Serializable

@Serializable
data class TongueTwister(
    val id: Int,
    val title: String,
    val content: String,
    val content2: String?,
)

fun TongueTwister.toTongueTwisterEntity() = TongueTwisterEntity(
    id = id,
    title = title,
    content = content,
    content2 = content2,
)
