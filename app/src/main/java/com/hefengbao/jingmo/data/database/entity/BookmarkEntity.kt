package com.hefengbao.jingmo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo("bookmarkable_id")
    val bookmarkableId: Int,
    @ColumnInfo("bookmarkable_model")
    val bookmarkableModel: String
)