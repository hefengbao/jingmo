package com.hefengbao.jingmo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dictionary")
data class DictionaryEntity(
    @PrimaryKey
    val id: Int,
    val char: String,
    val wubi: String?,
    val radical: String?,
    val stroke: Int,
    val pinyin: String?,
    @ColumnInfo("simple_explanation")
    val simpleExplanation: String?,
    val explanation: String?,
    val loanword: Boolean,
)
