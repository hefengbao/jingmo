package com.hefengbao.jingmo.data.model

import com.hefengbao.jingmo.data.database.entity.TagEntity
import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    val id: Long,
    val title: String
)

fun Tag.asTagEntity() = TagEntity(
    id, title
)
