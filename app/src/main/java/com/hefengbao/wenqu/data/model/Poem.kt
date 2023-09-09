package com.hefengbao.wenqu.data.model

import com.hefengbao.wenqu.data.database.entity.PoemEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 诗词文
 */
@Serializable
data class Poem(
    val id: Long,
    @SerialName("writer_id")
    val writerId: Long,
    val title: String,
    val content: String,
    val remark: String?,
    val translation: String?,
    val shangxi: String?,
    val bookmark: Boolean = false,
)

fun Poem.toPoemEntity() = PoemEntity(
    id, writerId, title, content, remark, translation, shangxi
)
