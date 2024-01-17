package com.hefengbao.jingmo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * 诗文书签
 */
@Entity(tableName = "writing_collections", indices = [Index("collected_at")])
data class WritingCollectionEntity(
    @PrimaryKey
    val id: Int
) {
    @ColumnInfo(name = "collected_at")
    var collectedAt: Long = System.currentTimeMillis()
}
