package com.hefengbao.jingmo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * 成语书签
 */
@Entity(tableName = "idiom_collections", indices = [Index("collected_at")])
data class IdiomCollectionEntity(
    @PrimaryKey
    val id: Int
) {
    @ColumnInfo(name = "collected_at")
    var collectedAt: Long = System.currentTimeMillis()
}
