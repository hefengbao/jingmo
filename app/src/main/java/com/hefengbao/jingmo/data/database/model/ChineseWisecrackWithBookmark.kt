package com.hefengbao.jingmo.data.database.model

import androidx.room.ColumnInfo

data class ChineseWisecrackWithBookmark(
    val id: Int,
    val riddle: String,
    val answer: String,
    @ColumnInfo(name = "first_word")
    val firstWord: String,
    @ColumnInfo(name = "first_letter")
    val firstLetter: String,
    @ColumnInfo("collected_at")
    val collectedAt: Long?
)
