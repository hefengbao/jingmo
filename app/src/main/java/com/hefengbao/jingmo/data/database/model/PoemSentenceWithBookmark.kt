package com.hefengbao.jingmo.data.database.model

import androidx.room.ColumnInfo

data class PoemSentenceWithBookmark(
    val id: Int,
    val content: String,
    val from: String,
    @ColumnInfo("poem_id")
    val poemId: Int?,
    @ColumnInfo("collected_at")
    val collectedAt: Long?
)
