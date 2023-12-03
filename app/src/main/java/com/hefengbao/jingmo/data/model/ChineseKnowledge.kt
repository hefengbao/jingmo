package com.hefengbao.jingmo.data.model

import com.hefengbao.jingmo.data.database.entity.ChineseKnowledgeEntity
import kotlinx.serialization.Serializable

@Serializable
data class ChineseKnowledge(
    val id: Int,
    val content: String,
    val label: String,
    val url: String?,
)

fun ChineseKnowledge.toChineseKnowledgeEntity() = ChineseKnowledgeEntity(
    id, content, label, url
)