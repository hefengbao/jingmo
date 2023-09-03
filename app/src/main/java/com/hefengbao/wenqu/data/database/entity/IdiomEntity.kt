package com.hefengbao.wenqu.data.database.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 成语
 */
@Serializable
data class IdiomEntity(
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
