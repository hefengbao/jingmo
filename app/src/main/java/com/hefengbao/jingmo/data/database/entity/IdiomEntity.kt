package com.hefengbao.jingmo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 成语
 */
@Entity(tableName = "idioms")
data class IdiomEntity(
    @PrimaryKey
    val id: Long,
    val word: String,
    val pinyin: String,
    val explanation: String,
    val example: String,
    val derivation: String,
    @ColumnInfo("first_word")
    val firstWord: String,
    @ColumnInfo("first_letter")
    val firstLetter: String
)
