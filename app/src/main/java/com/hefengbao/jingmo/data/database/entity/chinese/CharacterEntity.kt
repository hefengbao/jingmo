package com.hefengbao.jingmo.data.database.entity.chinese

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefengbao.jingmo.data.model.chinese.ExplanationItem

/**
 * 汉字
 */
@Entity(tableName = "chinese_character")
data class CharacterEntity(
    @PrimaryKey
    val id: Int,
    val character: String,
    val character2: String?,
    val pinyin: String?,
    val radical: String?,
    val stroke: Int?,
    val wubi: String?,
    val explanations: List<String>?,
    val explanations2: List<ExplanationItem>?
)