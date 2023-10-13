package com.hefengbao.jingmo.data.database.model

import androidx.room.ColumnInfo

data class PoemSimpleInfo(
    val id: Long,
    @ColumnInfo(name = "writer_name")
    val writerName: String,
    val dynasty: String,
    val title: String,
)
