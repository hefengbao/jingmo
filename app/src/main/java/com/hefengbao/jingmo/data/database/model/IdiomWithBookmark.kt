package com.hefengbao.jingmo.data.database.model

import androidx.room.ColumnInfo

data class IdiomWithBookmark(
    val id: Int,
    val word: String,
    val pinyin: String,
    val explanation: String,
    val example: String,
    val derivation: String,
    @ColumnInfo("first_word")
    val firstWord: String,
    @ColumnInfo("first_letter")
    val firstLetter: String,
    @ColumnInfo("collected_at")
    val collectedAt: Long?
)
