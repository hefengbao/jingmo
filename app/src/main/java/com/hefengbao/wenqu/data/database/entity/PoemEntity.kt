package com.hefengbao.wenqu.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 诗词文
 */
@Entity(tableName = "poems")
data class Poem(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "writer_id")
    val writerId: Long,
    val title: String,
    val content: String,
    val remark: String?,
    val translation: String?,
    val shangxi: String?,
    val bookmark: Boolean = false,
)
