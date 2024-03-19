package com.hefengbao.jingmo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Fts4
@Entity(tableName = "chinese_knowledge")
data class ChineseKnowledgeEntity(
    @PrimaryKey
    @ColumnInfo(name = "rowid")
    val id: Int,
    val content: String,
    val label: String,
    val url: String?,
)
