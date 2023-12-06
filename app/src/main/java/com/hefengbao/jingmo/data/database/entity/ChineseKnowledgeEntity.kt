package com.hefengbao.jingmo.data.database.entity

import androidx.room.Entity
import androidx.room.Fts4

@Fts4
@Entity(tableName = "chinese_knowledge")
data class ChineseKnowledgeEntity(
    val id: Int,
    val content: String,
    val label: String,
    val url: String?,
)
