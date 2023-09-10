package com.hefengbao.jingmo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 歇后语
 */
@Entity("chinese_wisecracks")
data class ChineseWisecrackEntity(
    @PrimaryKey
    val id: Long,
    val riddle: String,
    val answer: String,
    @ColumnInfo(name = "first_word")
    val firstWord: String,
    @ColumnInfo(name = "first_letter")
    val firstLetter: String
)
