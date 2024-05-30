package com.hefengbao.jingmo.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 词语
 */
@Entity(tableName = "chinese_expressions")
data class ChineseExpressionEntity(
    @PrimaryKey
    val id: Int,
    val word: String,
    val pinyin: String,
    val abbr: String? = null,
    val explanation: String? = null
)
