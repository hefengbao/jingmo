package com.hefengbao.jingmo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * 诗文名句书签
 */
@Entity(tableName = "poem_sentence_collections", indices = [Index("collected_at")])
data class PoemSentenceCollectionEntity(
    @PrimaryKey
    val id: Int
) {
    @ColumnInfo(name = "collected_at")
    var collectedAt: Long = System.currentTimeMillis()
}
