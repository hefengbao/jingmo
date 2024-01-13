package com.hefengbao.jingmo.data.model

import com.hefengbao.jingmo.data.database.entity.PoemTagCrossRef
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Deprecated("")
@Serializable
data class PoemTag(
    @SerialName("poem_id")
    val poemId: Long,
    @SerialName("tag_id")
    val tagId: Long
)

fun PoemTag.asPoemTagEntity() = PoemTagCrossRef(
    poemId, tagId
)
