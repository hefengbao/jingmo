package com.hefengbao.jingmo.data.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefengbao.jingmo.data.model.IdiomExample
import com.hefengbao.jingmo.data.model.IdiomQuote
import com.hefengbao.jingmo.data.model.IdiomSource

/**
 * 成语
 */
@Entity(tableName = "idioms")
data class IdiomEntity(
    @PrimaryKey
    val id: Int,
    val word: String,
    val pinyin: String,
    val abbr: String,
    val explanation: String?,
    @Embedded("source_")
    val source: IdiomSource?,
    @Embedded("quote_")
    val quote: IdiomQuote?,
    @Embedded("example_")
    val example: IdiomExample?,
    val similar: List<String>?,
    val opposite: List<String>?,
    val usage: String?,
    val story: List<String>?,
    val notice: String?,
)
