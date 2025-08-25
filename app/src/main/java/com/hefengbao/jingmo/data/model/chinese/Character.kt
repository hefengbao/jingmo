package com.hefengbao.jingmo.data.model.chinese

import com.hefengbao.jingmo.data.database.entity.chinese.CharacterEntity
import kotlinx.serialization.Serializable

/**
 * 汉字
 */
@Serializable
data class Character(
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

@Serializable
data class ExplanationItem(
    val same: String? = null,
    val content: String? = null,
    val detail: List<Detail>? = null,
    val words: List<Word>? = null,
    val example: String? = null,
    val refer: String? = null,
    val modern: String? = null,
    val speech: String? = null,
    val pinyin: String? = null,
    val simplified: String? = null,
    val variant: String? = null,
    val typo: String? = null
)

@Serializable
data class Detail(
    val text: String? = null,
    val book: String? = null,
)

@Serializable
data class Word(
    val word: String? = null,
    val text: String? = null
)

fun Character.asCharacterEntity() = CharacterEntity(
    id, character, character2, pinyin, radical, stroke, wubi, explanations, explanations2
)
