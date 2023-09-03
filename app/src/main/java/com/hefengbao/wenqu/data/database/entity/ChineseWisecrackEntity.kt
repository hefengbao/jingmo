package com.hefengbao.wenqu.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 歇后语
 */
@Entity("chinese_wisecracks")
data class ChineseWisecrack(
    @PrimaryKey
    val id: Long,
    val riddle: String,
    val answer: String
)
