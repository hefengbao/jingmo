package com.hefengbao.jingmo.data.database.model

import androidx.room.ColumnInfo

data class SimpleWritingInfo(
    @ColumnInfo(name = "rowid")
    val id: Int,
    val author: String,
    val dynasty: String,
    val type: String,
    val title: String
)
