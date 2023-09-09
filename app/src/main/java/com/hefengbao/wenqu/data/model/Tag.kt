package com.hefengbao.wenqu.data.model

import com.hefengbao.wenqu.data.database.entity.TagEntity
import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    val id: Long,
    val title: String
)

fun Tag.toTagEntity() = TagEntity(
    id, title
)
