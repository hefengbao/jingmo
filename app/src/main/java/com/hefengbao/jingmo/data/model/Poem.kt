package com.hefengbao.jingmo.data.model

import com.hefengbao.jingmo.data.database.entity.PoemEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 诗词文
 */
@Serializable
data class Poem(
    val id: Long,
    @SerialName("writer_id")
    val writerId: Long?,
    @SerialName("writer_name")
    val writerName: String,
    val dynasty: String,
    val title: String,
    val content: String,
    val remark: String?,
    val translation: String?,
    val shangxi: String?,
    val bookmark: Boolean = false,
)

fun Poem.asPoemEntity() = PoemEntity(
    id, writerId, writerName, dynasty, title, content, remark, translation, shangxi
)
