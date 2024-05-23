package com.hefengbao.jingmo.data.model

import com.hefengbao.jingmo.data.database.entity.DictionaryEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dictionary(
    val id: Int,
    val char: String,
    val wubi: String?,
    val radical: String?,
    val stroke: Int,
    val pinyin: String?,
    val pinyin2: List<String>?,
    @SerialName("simple_explanation")
    val simpleExplanation: String?,
    val explanation: String?,
    val loanword: Boolean,
)

fun Dictionary.asDictionaryEntity() = DictionaryEntity(
    id, char, wubi, radical, stroke, pinyin, simpleExplanation, explanation, loanword
)
