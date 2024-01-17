package com.hefengbao.jingmo.data.database.model

import androidx.room.ColumnInfo

data class WritingCollectionInfo(
    val id: Int,
    @ColumnInfo("collected_at")
    val collectedAt: Long,
    val author: String,
    val dynasty: String,
    val type: String,
    val title: String,
)
