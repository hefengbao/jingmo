package com.hefengbao.wenqu.data.model

import com.hefengbao.wenqu.data.database.entity.PoemTagCrossRef
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PoemTag(
    @SerialName("poem_id")
    val poemId: Long,
    @SerialName("tag_id")
    val tagId: Long
)

fun PoemTag.toPoemTagEntity() = PoemTagCrossRef(
    poemId, tagId
)
