package com.hefengbao.jingmo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4

/**
 * 诗词文
 */
@Deprecated("")
@Entity(tableName = "poems")
@Fts4
data class PoemEntity(
    val id: Long,
    @ColumnInfo(name = "writer_id")
    val writerId: Long?,
    @ColumnInfo(name = "writer_name")
    val writerName: String,
    val dynasty: String,
    val title: String,
    val content: String,
    val remark: String?,
    val translation: String?,
    val shangxi: String?
)
