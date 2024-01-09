package com.hefengbao.jingmo.data.model

import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 成语
 */
@Serializable
data class Idiom(
    val id: Long,
    val word: String,
    val pinyin: String,
    val explanation: String,
    val example: String,
    val derivation: String,
    @SerialName("first_word")
    val firstWord: String,
    @SerialName("first_letter")
    val firstLetter: String
)

fun Idiom.asIdiomEntity() = IdiomEntity(
    id, word, pinyin, explanation, example, derivation, firstWord, firstLetter
)
